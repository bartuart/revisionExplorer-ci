# revisionExplorer-ci
Continuous Integration for revisionExplorer project


# Make VMs UP and Running
```vagrant up```

# Install CI/CD tools
```vagrant ssh revisionExplorer-ansible```

```cd ansible```

```echo <Vault Password> > ../VAULT_PASSWORD_FILE ```

## Initial installation:

```ansible-playbook site.yml -i ./inventories/test --vault-password-file ../VAULT_PASSWORD_FILE```

## Updates:

```ansible-playbook site.yml -i ./inventories/test --vault-password-file ../VAULT_PASSWORD_FILE --tags=update```
