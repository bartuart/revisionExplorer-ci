pipelineJob('example') {
    definition {
        cpsScm {
            scm {
                git('https://github.com/bartuart/revisionExplorer.git')
            }
        }
    }
}