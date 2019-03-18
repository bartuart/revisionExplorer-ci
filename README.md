# revisionExplorer-ci
Continuous Integration for revisionExplorer project

#Requirements
* Vagrant [https://www.vagrantup.com/]
* Oracle VM VirtualBox [https://www.virtualbox.org/]


# Make VMs UP and Running
```vagrant up```

# Install CI/CD tools
```vagrant ssh revisionExplorer-ansible```

```cd ansible```

```echo <Vault Password> > ../VAULT_PASSWORD_FILE ```

```ansible-galaxy install -r requirements.yml ```

## Initial installation:

```ansible-playbook site.yml -i ./inventories/test --vault-password-file ../VAULT_PASSWORD_FILE```

## Updates:

```ansible-playbook site.yml -i ./inventories/test --vault-password-file ../VAULT_PASSWORD_FILE --tags=update```

## Jenkins update
To install/update plugins, agents:

```ansible-playbook jenkins-playbook.yml -i ./inventories/test --vault-password-file ../VAULT_PASSWORD_FILE```

To install/update global configuration, credentials and pipelines:

```ansible-playbook jenkins-playbook.yml -i ./inventories/test --vault-password-file ../VAULT_PASSWORD_FILE --tags=update```
