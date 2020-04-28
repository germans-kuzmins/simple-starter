package eu.simple.persistance

import org.hibernate.envers.RevisionNumber
import org.hibernate.envers.RevisionTimestamp
import java.time.Instant
import javax.persistence.*

@Entity
@SequenceGenerator(name = "Revision_seq_gen", sequenceName = "revision_seq")
@Table(name = "revinfo")
class Revision {
    @Id
    @GeneratedValue(
        generator = "Revision_seq_gen",
        strategy = GenerationType.SEQUENCE
    )
    @Column(name = "rev")
    @RevisionNumber
    val id: Long? = null

    @Column(name = "revision_date")
    @RevisionTimestamp
    val revisionDate: Instant = Instant.now()

    @Column(name = "user_name")
    var user = ""
}