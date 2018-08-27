apackage com.aitechwizards.secure.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.aitechwizards.secure.domain.Employee;
import com.aitechwizards.secure.service.IAddressService;
import com.aitechwizards.secure.service.IEmployeeService;

/**
 * @author aitechwizards
 *
 */
@Service("employeeService")
public class EmployeeService implements IEmployeeService {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private IAddressService addressService;

	@Override
	@PreAuthorize("hasRole('ROLE_USER')")
	@Transactional
	public List<Employee> getAllEmployees() {
		LOG.info("getAllEmployees  " + TransactionSynchronizationManager.getCurrentTransactionName());

		List<Employee> list = new ArrayList<>();
		list.add(new Employee(1, "Bharat"));
		list.add(new Employee(2, "test"));
		list.add(new Employee(3, "abc"));
		
		addressService.getAddress(); // Nested Transaction
		addressService.testSupport();  
		addressService.testMandatory();
		addressService.testRequired();
		this.selfInvocation(); // Self Invocation
		this.sayHayToEmp(); // private Access modifier
		return list;
	}

	/**
	 * This will not create new Transaction, but case Spring Transaction is working
	 * on proxy for self invocation it will create new Transcation
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String selfInvocation() {

		LOG.info(TransactionSynchronizationManager.getCurrentTransactionName());

		return null;
	}

	/**
	 * Transaction only supporting Public specifier. Here we are using private
	 * specifier. So it will not crate new Transaction
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private String sayHayToEmp() {

		LOG.info(TransactionSynchronizationManager.getCurrentTransactionName());

		return null;
	}

}
