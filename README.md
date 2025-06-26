Estrutura do Projeto

```bash
projeto-api/
├── .gradle/                    # Configurações internas do Gradle
├── clientes/
│   └── ClienteTarefa.java      # Cliente Java para testar a API via HttpURLConnection
├── src/
│   ├── main/
│   │   └── java/
│   │       └── app/
│   │           ├── Main.java   # Classe principal que inicializa o servidor Javalin
│   │           └── Tarefa.java # Classe que representa a entidade Tarefa
│   └── test/
│       └── java/
│           └── app/
│               └── MainTest.java # Testes JUnit para os endpoints REST
├── build.gradle                # Configurações de build do Gradle
├── settings.gradle             # Configurações do projeto
├── README.md                   # Este arquivo
└── prints/                     # Imagens de execução e testes (ver abaixo)
