# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.box = "ubuntu/precise32"

  config.vm.network "forwarded_port", guest: 9200, host: 9200
  config.vm.network "forwarded_port", guest: 5432, host: 5432
  config.vm.network "private_network", ip: "192.168.33.10"

  # config.vm.synced_folder "../data", "/vagrant_data"

  config.vm.provider "virtualbox" do |vb|
     # Customize the amount of memory on the VM:
     vb.customize ["modifyvm", :id, "--cpus", "2", "--memory", "2048"]
  end

  # puppet-bootstrap
  #installs puppet
 config.vm.provision "shell", path: "setup/ubuntu_bootstrap.sh"

 #installs puppert modules, oracle java 8
 config.vm.provision "shell", path: "setup/vm_provisioner.sh"

 config.vm.provision :puppet do |puppet|
   puppet.manifests_path = "puppet/manifests"
   puppet.manifest_file  = "init.pp"
   puppet.options        = [
                             '--verbose',
                             #'--debug',
                           ]
 end
end
