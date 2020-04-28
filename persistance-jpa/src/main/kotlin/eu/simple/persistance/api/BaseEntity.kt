package eu.simple.persistance.api

import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import eu.simple.persistance.AuditRevisionListener
import org.hibernate.annotations.OptimisticLock
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import org.hibernate.envers.RevisionEntity
import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import java.time.Instant
import javax.persistence.*

private const val STATIC_HASHCODE: Int = 31

@MappedSuperclass
@TypeDefs(
    TypeDef(name = "json", typeClass = JsonBinaryType::class)
)
@RevisionEntity(AuditRevisionListener::class)
abstract class BaseEntity<ID : Serializable> {

    @Version
    @Column(name = "entity_version", nullable = false)
    val entityVersion: Long = 0

    @OptimisticLock(excluded = true)
    @Column(name = "entity_created", nullable = false)
    val created: Instant = Instant.now()

    @OptimisticLock(excluded = true)
    @Column(name = "entity_updated", nullable = false)
    var updated: Instant = Instant.now()

    @Suppress("PropertyName")
    protected abstract var id: ID?

    val savedId: ID
        @Transient get() = checkNotNull(id) { "Entity has not been saved" }

    override fun toString(): String = "${javaClass.simpleName} { id: $id }"

    @PreUpdate
    fun onPreUpdate() {
        updated = Instant.now()
    }

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        javaClass != other?.let<Any, Class<*>>(ProxyUtils::getUserClass) -> false
        else -> {
            other as BaseEntity<*>
            id != null && id == other.id
        }
    }

    override fun hashCode(): Int {
        return STATIC_HASHCODE
    }
}