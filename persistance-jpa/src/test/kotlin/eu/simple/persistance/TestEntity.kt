package eu.simple.persistance

import eu.simple.persistance.api.BaseEntity
import eu.simple.persistance.api.JpaType
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "test_entity")
@SequenceGenerator(
    name = "TestEntity_seq_gen",
    sequenceName = "test_entity_seq",
    allocationSize = 5
)
class TestEntity(
    @Column(name = "string_property")
    var stringProperty: String,

    @Column(name = "json_property", columnDefinition=JpaType.JSONB)
    @Type(type = JpaType.JSON)
    val jsonProperty: MutableMap<String, Any> = mutableMapOf(),

    @Column(name = "jsonb_property", columnDefinition=JpaType.JSONB)
    @Type(type = JpaType.JSONB)
    val jsonbProperty: MutableMap<String, Any> = mutableMapOf()
) : BaseEntity<Long>() {

    @Id
    @GeneratedValue(generator = "TestEntity_seq_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    override var id: Long? = null

    override fun toString(): String {
        return "TestEntity(stringProperty='$stringProperty', id=$id)"
    }
}