Vagrant.require_version ">= 2.0.2"
VAGRANTFILE_API_VERSION = '2'
USER = 'vagrant'
SSH_KEYS = ENV['SSH_KEYS']
SSH_KEYS = "~/.ssh/" if SSH_KEYS.nil? || SSH_KEYS.empty?


Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.ssh.insert_key = false
  config.ssh.private_key_path = ["#{SSH_KEYS}id_rsa", "~/.vagrant.d/insecure_private_key"]
  
  #setup authentication with local ssh keys
  config.vm.provision "file", source: "#{SSH_KEYS}id_rsa.pub", destination: "~/.ssh/authorized_keys"
  config.vm.provision "file", source: "#{SSH_KEYS}id_rsa", destination: "~/.ssh/id_rsa"
  config.vm.provision "file", source: "#{SSH_KEYS}id_rsa.pub", destination: "~/.ssh/id_rsa.pub"
  
  
  #setup revisionExplorer-ci
  config.vm.define "revisionExplorer-ci" do |vm_config|
    vm_name = "revisionExplorer-ci"

    vm_config.vm.box = "bento/centos-7.6"
    vm_config.vm.network "private_network", ip: "10.0.0.201"
	vm_config.vm.network "forwarded_port", guest: 8080, host: 9080 #For Jenkins
	vm_config.vm.network "forwarded_port", guest: 8083, host: 9083 #For Nexus HTTPS
	vm_config.vm.network "forwarded_port", guest: 5000, host: 9000 #For Nexus HTTPS port for Docker registry
    vm_config.vm.hostname = vm_name

    vm_config.vm.provider "virtualbox" do |vb|
      vb.name = vm_name

      # Customize the amount of memory on the VM:
      vb.memory = "2048"
      vb.cpus = 2
    end
  end
  
  #setup revisionExplorer-test
  config.vm.define "revisionExplorer-test" do |vm_config|
    vm_name = "revisionExplorer-test"

    vm_config.vm.box = "bento/centos-7.6"
    vm_config.vm.network "private_network", ip: "10.0.0.202"
	vm_config.vm.network "forwarded_port", guest: 8080, host: 9085 #For Application
    vm_config.vm.hostname = vm_name

    vm_config.vm.provider "virtualbox" do |vb|
      vb.name = vm_name

      # Customize the amount of memory on the VM:
      vb.memory = "1024"
      vb.cpus = 1
    end
  end
  
  #setup revisionExplorer-qa
  config.vm.define "revisionExplorer-qa" do |vm_config|
    vm_name = "revisionExplorer-qa"

    vm_config.vm.box = "bento/centos-7.6"
    vm_config.vm.network "private_network", ip: "10.0.0.203"
	vm_config.vm.network "forwarded_port", guest: 8080, host: 9086 #For Application
    vm_config.vm.hostname = vm_name

    vm_config.vm.provider "virtualbox" do |vb|
      vb.name = vm_name

      # Customize the amount of memory on the VM:
      vb.memory = "1024"
      vb.cpus = 1
    end
  end
  
  #setup revisionExplorer-prod1
  config.vm.define "revisionExplorer-prod1" do |vm_config|
    vm_name = "revisionExplorer-prod1"

    vm_config.vm.box = "bento/centos-7.6"
    vm_config.vm.network "private_network", ip: "10.0.0.204"
	vm_config.vm.network "forwarded_port", guest: 8080, host: 9087 #For Application
    vm_config.vm.hostname = vm_name

    vm_config.vm.provider "virtualbox" do |vb|
      vb.name = vm_name

      # Customize the amount of memory on the VM:
      vb.memory = "1024"
      vb.cpus = 1
    end
  end
  
  #setup revisionExplorer-prod2
  config.vm.define "revisionExplorer-prod2" do |vm_config|
    vm_name = "revisionExplorer-prod2"

    vm_config.vm.box = "bento/centos-7.6"
    vm_config.vm.network "private_network", ip: "10.0.0.205"
	vm_config.vm.network "forwarded_port", guest: 8080, host: 9088 #For Application
    vm_config.vm.hostname = vm_name

    vm_config.vm.provider "virtualbox" do |vb|
      vb.name = vm_name

      # Customize the amount of memory on the VM:
      vb.memory = "1024"
      vb.cpus = 1
    end
  end
  
   #setup revisionExplorer-ansible
  config.vm.define "revisionExplorer-ansible" do |vm_config|
    vm_name = "revisionExplorer-ansible"

    vm_config.vm.box = "bento/centos-7.6"
    vm_config.vm.network "private_network", ip: "10.0.0.206"
    vm_config.vm.hostname = vm_name
	
	vm_config.vm.provision "shell", path: "install-ansible.sh"

    vm_config.vm.provider "virtualbox" do |vb|
      vb.name = vm_name

      # Customize the amount of memory on the VM:
      vb.memory = "2048"
      vb.cpus = 2
    end
	
	vm_config.vm.synced_folder File.expand_path('~/Dropbox/self-development/evolution_plan/developments/revisionExplorer-ci'), "/home/#{USER}/ansible"
  end
  
end