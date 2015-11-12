class apt_update {
  exec { "aptGetUpdate":
    command => "sudo apt-get update",
    path    => ["/bin", "/usr/bin"]
  }
}

class othertools {
  package { "git":
    ensure  => latest,
    require => Exec["aptGetUpdate"]
  }

  package { "vim-common":
    ensure  => latest,
    require => Exec["aptGetUpdate"]
  }

  package { "curl":
    ensure  => present,
    require => Exec["aptGetUpdate"]
  }

  package { "htop":
    ensure  => present,
    require => Exec["aptGetUpdate"]
  }

  package { "tree":
    ensure  => present,
    require => Exec["aptGetUpdate"]
  }
}

class { 'elasticsearch':
  package_url   => 'https://download.elasticsearch.org/elasticsearch/elasticsearch/elasticsearch-1.7.3.deb',
  config        => {
    'cluster.name'           => 'sakila-cluster',
    'http.enabled'           => true,
    'http.cors.enabled'      => true,
    'http.cors.allow-origin' => '"*"',
    'network'                => {
      'host' => '0.0.0.0',
    },
  },
  init_defaults => { }, # Init defaults hash
}

elasticsearch::instance { 'es-01':
  #datadir => '/var/lib/elasticsearch-data',
}

elasticsearch::plugin{ 'mobz/elasticsearch-head':
  module_dir => 'head',
  instances  => [ 'es-01' ],
}

class { 'postgresql::globals':
  manage_package_repo => true,
  version             => '9.4',
} ->
class { 'postgresql::server':
  listen_addresses  => '*',
  postgres_password => 'postgresql',
}->
postgresql::server::db { 'sakila':
  user     => 'sakila_user',
  password => postgresql_password('sakila_user', 's@kil@_us3r'),
  #grant         => 'all',
}->
postgresql::server::db { 'imdb':
  user     => 'imdb_user',
  password => postgresql_password('imdb_user', 'imdb_us3r'),
  #grant         => 'all',
}->
postgresql::server::database_grant { 'sakila':
  privilege => 'ALL',
  db        => 'sakila',
  role      => 'sakila_user',
}->
postgresql::server::database_grant { 'imdb':
  privilege => 'ALL',
  db        => 'imdb',
  role      => 'imdb_user',
}

# rule for remote connections
postgresql::server::pg_hba_rule { 'allow remote connections with password':
  type        => 'host',
  database    => 'all',
  user        => 'all',
  address     => 'all',
  auth_method => 'md5',
}
