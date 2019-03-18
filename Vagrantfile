Vagrant.require_version ">= 2.0.2"
VAGRANTFILE_API_VERSION = '2'
USER = 'vagrant'
SSH_KEYS = ENV['SSH_KEYS']
SSH_KEYS = "~/.ssh/" if SSH_KEYS.nil? || SSH_KEYS.empty?

#Commands for provision
ANSIBLE_INSTALL_COMMAND = 'yum install -y epel-release && yum install -y ansible'
DISABLE_SWAP = 'swapoff -a'
NORMALIZE_SSH_KEYS = 'chmod -R 700 /home/vagrant/.ssh && chmod 600 /home/vagrant/.ssh/id_rsa && chmod 644 /home/vagrant/.ssh/id_rsa.pub'
ADD_KEYS_TO_KNOWN_HOSTS = 'ssh-keyscan -H 10.0.0.201 >> /home/vagrant/.ssh/known_hosts && ssh-keyscan -H 10.0.0.202 >> /home/vagrant/.ssh/known_hosts && ssh-keyscan -H 10.0.0.203 >> /home/vagrant/.ssh/known_hosts'


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
    vm_config.vm.hostname = vm_name

    vm_config.vm.provider "virtualbox" do |vb|
      vb.name = vm_name

      # Customize the amount of memory on the VM:
      vb.memory = "2048"
      vb.cpus = 2
    end
  end
  
  #setup revisionExplorer-kube-master
  config.vm.define "revisionExplorer-kube-master" do |vm_config|
    vm_name = "revisionExplorer-kube-master"

    vm_config.vm.box = "bento/centos-7.6"
	vm_config.vm.network "private_network", ip: "10.0.0.202"
    vm_config.vm.hostname = vm_name
	
	vm_config.vm.provision "shell", :run => 'always', inline: "#{DISABLE_SWAP}"

    vm_config.vm.provider "virtualbox" do |vb|
      vb.name = vm_name

      # Customize the amount of memory on the VM:
      vb.memory = "2048"
      vb.cpus = 1
    end
  end
  
  #setup revisionExplorer-kube-node
  config.vm.define "revisionExplorer-kube-node" do |vm_config|
    vm_name = "revisionExplorer-kube-node"

    vm_config.vm.box = "bento/centos-7.6"
    vm_config.vm.network "private_network", ip: "10.0.0.203"
    vm_config.vm.hostname = vm_name
	
	vm_config.vm.provision "shell", :run => 'always', inline: "#{DISABLE_SWAP}"

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
    vm_config.vm.network "private_network", ip: "10.0.0.204"
    vm_config.vm.hostname = vm_name
	
	vm_config.vm.provision "shell", inline: "#{ANSIBLE_INSTALL_COMMAND}"
	vm_config.vm.provision "shell", :run => 'always', inline: "#{NORMALIZE_SSH_KEYS}"
	vm_config.vm.provision "shell", :run => 'always', inline: "#{ADD_KEYS_TO_KNOWN_HOSTS}"

    vm_config.vm.provider "virtualbox" do |vb|
      vb.name = vm_name

      # Customize the amount of memory on the VM:
      vb.memory = "2048"
      vb.cpus = 2
    end
	
	vm_config.vm.synced_folder File.expand_path('~/Dropbox/self-development/evolution_plan/developments/revisionExplorer-ci'), "/home/#{USER}/ansible"
  end
  
end