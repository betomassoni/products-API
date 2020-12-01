
Vagrant.configure("2") do |config|

    config.vm.define "xy-inc" do |config|
        config.vm.provider :digital_ocean do |provider, override|
          override.ssh.private_key_path = '~/.ssh/vagrant_id_rsa'
          override.vm.box = 'digital_ocean'
          override.vm.box_url = "https://github.com/devopsgroup-io/vagrant-digitalocean/raw/master/box/digital_ocean.box"
          override.nfs.functional = false
          override.vm.allowed_synced_folder_types = :rsync
          provider.token = '[YOUR_TOKEN]'
          provider.image = 'ubuntu-18-04-x64'
          provider.region = 'nyc1'
          provider.size = 's-1vcpu-1gb' 
          provider.backups_enabled = false
          provider.private_networking = false 
          provider.ipv6 = false
          provider.monitoring = false
        end
  
        config.vm.provision "shell", 
              inline: "apt-get update && apt-get install -y docker.io"  
  
        config.vm.provision "shell",
              inline: "curl -L https://github.com/docker/compose/releases/download/1.15.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose && \
                       chmod +x /usr/local/bin/docker-compose"
        
        
        config.vm.provision "shell",
          inline: "docker-compose -f /vagrant/xy-inx/docker-compose.yml build && docker-compose -f /vagrant/xy-inx/docker-compose.yml up -d"  
  
    end
  
  end