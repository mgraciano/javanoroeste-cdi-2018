# Exemplos sobre CDI: Apresentação Java Noroeste

Os exemplos deste projeto são relacionados a apresentação feita pelo Java Noroeste em 2018 sobre CDI.
A base do projeto utiliza o framework Wildfly Swarm.

O código dos exemplos foram inspirados no exemplo [JPA, JAX-RS and CDI .war Example](https://github.com/wildfly-swarm/wildfly-swarm-examples/tree/master/jpa-jaxrs-cdi/jpa-jaxrs-cdi)
do próprio Wildfly Swarm e de outra apresentação feita no [JavaOne 2012](https://github.com/mgraciano/javaone-2012).

## Execução

Você pode executá-lo de várias formas:

* mvn package && java -jar ./target/cdi-exemplos-swarm.jar
* mvn wildfly-swarm:run

## Uso

    http://localhost:8080/
