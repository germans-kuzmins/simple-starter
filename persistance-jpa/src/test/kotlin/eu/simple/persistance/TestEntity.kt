package eu.simple.persistance

import eu.simple.persistance.api.BaseEntity
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
    var stringProperty: String
) : BaseEntity<Long>() {

    @Id
    @GeneratedValue(generator = "TestEntity_seq_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    override var id: Long? = null

    override fun toString(): String {
        return "TestEntity(stringProperty='$stringProperty', id=$id)"
    }
}