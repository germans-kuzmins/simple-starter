package eu.simple.persistance

import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.PostgreSQLContainer

private const val postgressVersion = "postgres:12-alpine"
object  SimplePostgresqlContainer : PostgreSQLContainer<SimplePostgresqlContainer>(
    postgressVersion
),
    BeforeAllCallback {
    override fun start() {
        super.start()
        System.setProperty("DB_URL", this.jdbcUrl);
        System.setProperty("DB_USERNAME", this.username);
        System.setProperty("DB_PASSWORD", this.password);
    }

    override fun stop() {
        // do nothing, JVM handles shut down
    }

    override fun beforeAll(context: ExtensionContext?) {
        start()
    }
}