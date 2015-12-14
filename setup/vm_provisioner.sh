#!/usr/bin/env bash
# this script installs the puppet modules we need,
#and tries to do tricks with setting local repository for ubuntu updates

# Install wget

sudo apt-get install -qy wget;

sed -e '/templatedir/ s/^#*/#/' -i.back /etc/puppet/puppet.conf

## set local/fastest mirror and local timezone
mv /etc/apt/sources.list /etc/apt/sources.list.orig
cat > /etc/apt/sources.list <<EOF
deb mirror://mirrors.ubuntu.com/mirrors.txt trusty main restricted universe multiverse
deb mirror://mirrors.ubuntu.com/mirrors.txt trusty-updates main restricted universe multiverse
deb mirror://mirrors.ubuntu.com/mirrors.txt trusty-backports main restricted universe multiverse
deb mirror://mirrors.ubuntu.com/mirrors.txt trusty-security main restricted universe multiverse
EOF

## Add the oracle java repository
add-apt-repository ppa:webupd8team/java

sudo apt-get update
#export tz=`wget -qO - http://geoip.ubuntu.com/lookup | sed -n -e 's/.*<TimeZone>\(.*\)<\/TimeZone>.*/\1/p'` &&  sudo timedatectl set-timezone $tz

mkdir -p /etc/puppet/modules;
echo "Beginning install puppet modules..."

if [ ! -d /etc/puppet/modules/stdlib ]; then
    echo "Installing stdlib module..."
    puppet module install puppetlabs-stdlib
    echo "Done installing stdlib."
fi


if [ ! -d /etc/puppet/modules/apt ]; then
    echo "Installing apt module..."
    puppet module install puppetlabs-apt
    echo "Done installing apt."
fi

if [ ! -d /etc/puppet/modules/epel ]; then
    echo "Installing epel module..."
    puppet module install stahnma-epel
    echo "Done installing epel."
fi

if [ ! -d /etc/puppet/modules/firewall ]; then
    echo "Installing firewall module..."
    puppet module install puppetlabs-firewall --version 1.7.1
    echo "Done installing firewall."
fi

if [ ! -d /etc/puppet/modules/postgresql ]; then
    echo "Installing postgresql module..."
    puppet module install puppetlabs-postgresql --version 4.6.0
    echo "Done installing postgresql."
fi

if [ ! -d /etc/puppet/modules/concat ]; then
    echo "Installing concat module..."
    puppet module install puppetlabs-concat --version 1.2.4
    echo "Done installing concat."
fi

if [ ! -d /etc/puppet/modules/elasticsearch ]; then
    echo "Installing elasticsearch module..."
    puppet module install elasticsearch-elasticsearch -v 0.9.9 --ignore-dependencies
    echo "Done installing elasticsearch."
fi

if [ ! -d /etc/puppet/modules/redis ]; then
    echo "Installing redis module..."
    puppet module install arioch-redis
    echo "Done installing redis."
fi

echo "done installing all puppet modules."

#if [ ! -d /etc/puppet/modules/logstash ]; then
# puppet module install elasticsearch-logstash
#fi

## Install oracle java 8
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
apt-get -y install oracle-java8-installer
apt-get -y install oracle-java8-set-default
