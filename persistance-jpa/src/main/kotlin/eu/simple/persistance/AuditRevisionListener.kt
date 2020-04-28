package eu.simple.persistance;

import eu.simple.persistance.api.RevisionHelper;
import org.hibernate.envers.RevisionListener;

internal class AuditRevisionListener(
	private val revisionHelper: RevisionHelper
): RevisionListener {

	override fun newRevision(revisionEntity: Any?) {
		checkNotNull(revisionEntity)
		check(revisionEntity is Revision)
		val modifier = revisionHelper.getCurrentUserName()
		revisionEntity.user = modifier
	}
}
