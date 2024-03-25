# Spring Environment

## Priority Order of Reading Properties

### Command-line parameters via --

### Inline JSON

### Java System Properties ` System.getProperties() `

### Operating System Environment Variables ` System.getEnv() `

### ` RandomValuePropertySource ` for Returning Random Numbers

### ` application.properties ` File

### ` application.yml ` File

### Custom Files
- Enumerated using ` @PropertySource `
- and then attached to a ` @Configuration `

