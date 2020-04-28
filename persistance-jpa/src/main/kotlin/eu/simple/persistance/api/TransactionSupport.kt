package eu.simple.persistance.api

import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute
import org.springframework.transaction.support.DefaultTransactionDefinition
import org.springframework.transaction.support.TransactionTemplate
import javax.transaction.Transactional.TxType

@Component
class TransactionSupport(m: PlatformTransactionManager) {
	init {
		manager = m
	}

	companion object {
		private lateinit var manager: PlatformTransactionManager

		fun <R> inTransaction(
			txType: TxType = TxType.REQUIRED,
			readOnly: Boolean = false,
			block: (TransactionStatus) -> R?
		): R = TransactionTemplate(manager, buildTransactionDefinition(txType, readOnly)).execute(block)!!

		private fun buildTransactionDefinition(txType: TxType, readOnly: Boolean): TransactionDefinition =
			RuleBasedTransactionAttribute().apply {
				isReadOnly = readOnly
				setPropagationBehaviorName(DefaultTransactionDefinition.PREFIX_PROPAGATION + txType.toString())
			}
	}
}

