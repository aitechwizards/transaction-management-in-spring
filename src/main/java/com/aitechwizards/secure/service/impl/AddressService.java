package com.aitechwizards.secure.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.aitechwizards.secure.service.IAddressService;

@Service(value = "addressService")
public class AddressService implements IAddressService {
	private static final Logger LOG = LoggerFactory.getLogger(AddressService.class);

	/**
	 * Nested Transaction: Propagation.REQUIRES_NEW will create every time new
	 * transaction (non-Javadoc)
	 * 
	 * @see com.aitechwizards.secure.service.IAddressService#getAddress()
	 */
	@Override
	@PreAuthorize("hasRole('ROLE_USER')")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void getAddress() {
		LOG.info("getAddress " + TransactionSynchronizationManager.getCurrentTransactionName());
		this.checkTransactionAccessModifer();
	}

	/**
	 * This will use existing Transaction if exist, if not present it's running
	 * Non-Transactional.
	 * 
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void testSupport() {
		LOG.info("testSupport >>> ", TransactionSynchronizationManager.getCurrentTransactionName());
	}

	/**
	 * This will use existing Transaction if exist else it will create new .
	 * 
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void testRequired() {
		LOG.info("testRequired >>> ", TransactionSynchronizationManager.getCurrentTransactionName());
	}

	/**
	 * This will support current transaction, if not exist it will throw Exception
	 * 
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void testMandatory() {
		LOG.info("testMandatory >>> ", TransactionSynchronizationManager.getCurrentTransactionName());

	}

	/**
	 * Transaction will is only on public methods.
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private String checkTransactionAccessModifer() {
		LOG.info("testMandatory >>> ", TransactionSynchronizationManager.getCurrentTransactionName());

		return "Hello ?";
	}
}
