## Alternative Schaulspieler(?)/Charakter-Bezeichnungen
CREATE TABLE IF NOT EXISTS `aka_name` (
  `id` int(11) NOT NULL auto_increment,
  `person_id` int(11) NOT NULL,                            ## FOREIGN KEY(name.id)
  `name` text NOT NULL,                                            ## alternativer Name von Schauspieler(?)/Charakter
  `imdb_index` varchar(12) default NULL,        ## meistens NULL
  `name_pcode_cf` varchar(5) default NULL,    ## Soundex von name
  `name_pcode_nf` varchar(5) default NULL,    ## Soundex von name
  `surname_pcode` varchar(5) default NULL,    ## Soundex des Nachnamens aus name
  PRIMARY KEY  (`id`),
  KEY `idx_person` (`person_id`),
  KEY `idx_pcodecf` (`name_pcode_cf`),
  KEY `idx_pcodenf` (`name_pcode_nf`),
  KEY `idx_pcode` (`surname_pcode`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=516315 ;


## Alternative Film/Serien-Bezeichnungen
CREATE TABLE IF NOT EXISTS `aka_title` (
  `id` int(11) NOT NULL auto_increment,
  `movie_id` int(11) default NULL,                    ## FOREIGN KEY(title.id)
  `title` text NOT NULL,                                        ## Name der Serie/des Films
  `imdb_index` varchar(12) default NULL,        ## fast immer NULL (sonst römische Zahlen)
  `kind_id` int(11) NOT NULL,                                ## FOREIGN KEY(kind_type.id)
  `production_year` int(11) default NULL,        ## Produktionsjahr
  `phonetic_code` varchar(5) default NULL,    ## Soundex von title
  `episode_of_id` int(11) default NULL,            ## FOREIGN KEY(title.id)
  `season_nr` int(11) default NULL,                    ## Season-Nr.
  `episode_nr` int(11) default NULL,                ## Episoden-Nr.
  `note` text,                                                            ## Notiz
  PRIMARY KEY  (`id`),
  KEY `idx_movieid` (`movie_id`),
  KEY `idx_pcode` (`phonetic_code`),
  KEY `idx_epof` (`episode_of_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=271577 ;


CREATE TABLE IF NOT EXISTS `cast_info` (
  `id` int(11) NOT NULL auto_increment,
  `person_id` int(11) NOT NULL,                            ## FOREIGN KEY(name.id)
  `movie_id` int(11) NOT NULL,                            ## FOREIGN KEY(title.id)
  `person_role_id` int(11) default NULL,        ## FOREIGN KEY(char_name.id)
  `note` text,                                                            ## Text
  `nr_order` int(11) default NULL,                    ## ????????????????????????
  `role_id` int(11) NOT NULL,                                ## FOREIGN KEY(role_type.id)
  PRIMARY KEY  (`id`),
  KEY `idx_pid` (`person_id`),
  KEY `idx_mid` (`movie_id`),
  KEY `idx_cid` (`person_role_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16523187 ;


## Namen der Schaulspieler, Regisseure... ?!
CREATE TABLE IF NOT EXISTS `char_name` (
  `id` int(11) NOT NULL auto_increment,
  `name` text NOT NULL,                                            ## Namen von Regisseuren, Produzenten, Schauspielern, ..., jedoch keine Film/Serien, ...-Namen!
  `imdb_index` varchar(12) default NULL,        ## fast immer NULL (sonst römische Zahlen)
  `imdb_id` int(11) default NULL,                        ## immer NULL
  `name_pcode_nf` varchar(5) default NULL,    ## Soundex von name im normalen Format
  `surname_pcode` varchar(5) default NULL,    ## Soundex des Nachnamens aus name, falls es von name_pcode_nf abweicht
  PRIMARY KEY  (`id`),
  KEY `idx_name` (`name`(6)),
  KEY `idx_pcodenf` (`name_pcode_nf`),
  KEY `idx_pcode` (`surname_pcode`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2083542 ;


## Filmstudios/Firmen
CREATE TABLE IF NOT EXISTS `company_name` (
  `id` int(11) NOT NULL auto_increment,
  `name` text NOT NULL,                                            ## Name des Filmstudios/einer Firma z.B. "Warner Bros."
  `country_code` varchar(255) default NULL,    ## z.B. "[de]" oder "[us]"
  `imdb_id` int(11) default NULL,                        ## immer NULL
  `name_pcode_nf` varchar(5) default NULL,    ## Soundex von name im normalen Format
  `name_pcode_sf` varchar(5) default NULL,    ## Soundex von name plus Ländercode
  PRIMARY KEY  (`id`),
  KEY `idx_name` (`name`(6)),
  KEY `idx_pcodenf` (`name_pcode_nf`),
  KEY `idx_pcodesf` (`name_pcode_sf`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=194732 ;


## Art der Firma  
CREATE TABLE IF NOT EXISTS `company_type` (
  `id` int(11) NOT NULL auto_increment,
  `kind` varchar(32) NOT NULL,                            ## one of 1-4 / SET z.B. 'production companies'
  PRIMARY KEY  (`id`),
  UNIQUE KEY `kind` (`kind`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;


CREATE TABLE IF NOT EXISTS `complete_cast` (
  `id` int(11) NOT NULL auto_increment,
  `movie_id` int(11) default NULL,                    ## FOREIGN KEY(title.id)
  `subject_id` int(11) NOT NULL,                        ## FOREIGN KEY(comp_cast_type.id)
  `status_id` int(11) NOT NULL,                            ## FOREIGN KEY(comp_cast_type.id)
  PRIMARY KEY  (`id`),
  KEY `idx_mid` (`movie_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=78838 ;


CREATE TABLE IF NOT EXISTS `comp_cast_type` (
  `id` int(11) NOT NULL auto_increment,
  `kind` varchar(32) NOT NULL,                            ## one of 1-4 / SET
  PRIMARY KEY  (`id`),
  UNIQUE KEY `kind` (`kind`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;


CREATE TABLE IF NOT EXISTS `info_type` (
  `id` int(11) NOT NULL auto_increment,                            
  `info` varchar(32) NOT NULL,                            ## one of 1-113 / SET
  PRIMARY KEY  (`id`),
  UNIQUE KEY `info` (`info`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=114 ;

## Art des Eintrages
CREATE TABLE IF NOT EXISTS `kind_type` (
  `id` int(11) NOT NULL auto_increment,
  `kind` varchar(15) NOT NULL,                            ## one of 1-7 / SET z.B. 'movie', 'tv series', 'video games'
  PRIMARY KEY  (`id`),
  UNIQUE KEY `kind` (`kind`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;


CREATE TABLE IF NOT EXISTS `link_type` (
  `id` int(11) NOT NULL auto_increment,
  `link` varchar(32) NOT NULL,                            ## one of 1-18 / SET    
  PRIMARY KEY  (`id`),
  UNIQUE KEY `link` (`link`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;


## Relation Film <-> Firma
CREATE TABLE IF NOT EXISTS `movie_companies` (
  `id` int(11) NOT NULL auto_increment,
  `movie_id` int(11) NOT NULL,                            ## FOREIGN KEY(title.id)
  `company_id` int(11) NOT NULL,                        ## FOREIGN KEY(name.id)
  `company_type_id` int(11) NOT NULL,                ## FOREIGN KEY(company_type.id)
  `note` text,                                                            ## Anmerkung
  PRIMARY KEY  (`id`),
  KEY `idx_mid` (`movie_id`),
  KEY `idx_cid` (`company_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1482933 ;


## Filmdetails, wie z.B. Laufzeit oder Seitenverhältnis
CREATE TABLE IF NOT EXISTS `movie_info` (
  `id` int(11) NOT NULL auto_increment,
  `movie_id` int(11) NOT NULL,                            ## FOREIGN KEY(title.id)
  `info_type_id` int(11) NOT NULL,                    ## FOREIGN KEY(info_type.id)
  `info` text NOT NULL,                                            ## Text
  `note` text,                                                            ## Anmerkung
  PRIMARY KEY  (`id`),
  KEY `idx_mid` (`movie_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10449698 ;


## Relation Filme <-> Filme: Ãƒâ€žhnliche Filme
CREATE TABLE IF NOT EXISTS `movie_link` (
  `id` int(11) NOT NULL auto_increment,
  `movie_id` int(11) NOT NULL,                            ## FOREIGN KEY(title.id)
  `linked_movie_id` int(11) NOT NULL,                ## FOREIGN KEY(title.id)
  `link_type_id` int(11) NOT NULL,                    ## FOREIGN KEY(link_type.id)
  PRIMARY KEY  (`id`),
  KEY `idx_mid` (`movie_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=789129 ;


## Namen von Personen und Charakteren (?)
CREATE TABLE IF NOT EXISTS `name` (
  `id` int(11) NOT NULL auto_increment,
  `name` text NOT NULL,                                            ## Namen von Regisseuren, Produzenten, Schauspielern, ...
  `imdb_index` varchar(12) default NULL,        ## häufig NULL (sonst: römische Zahlen)
  `imdb_id` int(11) default NULL,                        ## immer NULL
  `name_pcode_cf` varchar(5) default NULL,    ## Soundex von name im kanonischen (canonical) Format
  `name_pcode_nf` varchar(5) default NULL,    ## Soundex von name im normalen Format, falls es sich von name_pcode_cf unterscheidet
  `surname_pcode` varchar(5) default NULL,    ## Soundex des Nachnamens aus name, falls es sich von name_pcode_cf und name_pcode_nf unterscheidet
  PRIMARY KEY  (`id`),
  KEY `idx_name` (`name`(6)),                                
  KEY `idx_pcodecf` (`name_pcode_cf`),
  KEY `idx_pcodenf` (`name_pcode_nf`),
  KEY `idx_pcode` (`surname_pcode`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2305107 ;


## Art der Personen, z.B. Charakter, Schauspieler, Regisseur, ...
CREATE TABLE IF NOT EXISTS `person_info` (
  `id` int(11) NOT NULL auto_increment,
  `person_id` int(11) NOT NULL,                            ## FOREIGN KEY(name.id)
  `info_type_id` int(11) NOT NULL,                    ## FOREIGN KEY(info_type.id)
  `info` text NOT NULL,                                            ## Text
  `note` text,                                                            ## Anmerkung
  PRIMARY KEY  (`id`),
  KEY `idx_pid` (`person_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1918571 ;


CREATE TABLE IF NOT EXISTS `role_type` (
  `id` int(11) NOT NULL auto_increment,
  `role` varchar(32) NOT NULL,                            ## one of 1-12 / SET
  PRIMARY KEY  (`id`),
  UNIQUE KEY `role` (`role`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;


## Filme/Serien/Spiele
CREATE TABLE IF NOT EXISTS `title` (
  `id` int(11) NOT NULL auto_increment,
  `title` text NOT NULL,                                        ## Filmtitel, Serientitel, ...
  `imdb_index` varchar(12) default NULL,        ## fast immer NULL (sonst: römische Zahlen)
  `kind_id` int(11) NOT NULL,                                ## FOREIGN KEY(kind_type.id)
  `production_year` int(11) default NULL,        ## Produktionsjahr
  `imdb_id` int(11) default NULL,                        ## immer NULL
  `phonetic_code` varchar(5) default NULL,    ## Soundex von title
  `episode_of_id` int(11) default NULL,            ## FOREIGN KEY(title.id)
  `season_nr` int(11) default NULL,                    ## Season-Nr.
  `episode_nr` int(11) default NULL,                ## Episoden-Nr.
  `series_years` varchar(49) default NULL,    ## Ausstrahlung der Serie (z.B. "2008-????")
  PRIMARY KEY  (`id`),
  KEY `idx_title` (`title`(10)),
  KEY `idx_pcode` (`phonetic_code`),
  KEY `idx_epof` (`episode_of_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1231480 ;