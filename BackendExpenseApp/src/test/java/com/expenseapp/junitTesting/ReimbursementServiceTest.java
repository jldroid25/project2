package com.expenseapp.expenseapp.junitTesting;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.expenseapp.expenseapp.dao.ReimbRepositoryDao;
import com.expenseapp.expenseapp.entity.Reimbursement;
import com.expenseapp.expenseapp.exception.ApplicationException;
import com.expenseapp.expenseapp.pojo.ReimbursementPojo;
import com.expenseapp.expenseapp.service.ReimbursementServiceImpl;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.mockito.junit.jupiter.MockitoExtension;

@ContextConfiguration(classes = { ReimbursementServiceImpl.class })
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@Slf4j
public class ReimbursementServiceTest {

	@MockBean
	ReimbRepositoryDao reimbRepositoryDao;

	@InjectMocks
	ReimbursementServiceImpl reimbursementServiceImpl;

	@BeforeAll
	static void setup() {

		System.out.println("@BeforeAll - executes once before all test methods in this class");
	}

	@BeforeEach
	void init() {
		System.out.println("@BeforeEach - executes before each test method in this class");
	}

	@Test
	void testCreateReimbursementService() throws ApplicationException {
		Reimbursement reimbursement = new Reimbursement();
		reimbursement.setReimbId(123);
		reimbursement.setReimbReason("Just cause");
		reimbursement.setReimbStatus("Reimb Status");
		reimbursement.setReimbAmount(10.0f);
		reimbursement.setUserId(123);
		LocalDateTime atStartOfDayResult = LocalDate.of(2021, 12, 12).atStartOfDay();
		reimbursement.setReimbDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		reimbursement.setReimbRemoved(true);
		when(this.reimbRepositoryDao.saveAndFlush((Reimbursement) any())).thenReturn(reimbursement);
		ReimbursementPojo reimbursementPojo = new ReimbursementPojo();
		ReimbursementPojo actualCreateReimbursementServiceResult = this.reimbursementServiceImpl
				.createReimbursementService(reimbursementPojo);
		assertSame(reimbursementPojo, actualCreateReimbursementServiceResult);
		assertEquals(123, actualCreateReimbursementServiceResult.getReimbId());
		verify(this.reimbRepositoryDao).saveAndFlush((Reimbursement) any());
		List<ReimbursementPojo> expectedAllResolvedReimbursementService = this.reimbursementServiceImpl
				.getAllPendingReimbursementService();
		assertEquals(expectedAllResolvedReimbursementService,
				this.reimbursementServiceImpl.getAllResolvedReimbursementService());
	}

	@Test
	void testUpdateReimbursementService() throws ApplicationException {
		Reimbursement reimbursement = new Reimbursement();
		reimbursement.setReimbId(444);
		reimbursement.setReimbReason("Just cause");
		reimbursement.setReimbStatus("Reimb Status");
		reimbursement.setReimbAmount(10.0f);
		reimbursement.setUserId(444);
		LocalDateTime atStartOfDayResult = LocalDate.of(2021, 12, 12).atStartOfDay();
		reimbursement.setReimbDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		reimbursement.setReimbRemoved(true);
		when(this.reimbRepositoryDao.save((Reimbursement) any())).thenReturn(reimbursement);
		ReimbursementPojo reimbursementPojo = new ReimbursementPojo();
		assertSame(reimbursementPojo, this.reimbursementServiceImpl.updateReimbursementService(reimbursementPojo));
		verify(this.reimbRepositoryDao).save((Reimbursement) any());
		List<ReimbursementPojo> expectedAllResolvedReimbursementService = this.reimbursementServiceImpl
				.getAllPendingReimbursementService();
		assertEquals(expectedAllResolvedReimbursementService,
				this.reimbursementServiceImpl.getAllResolvedReimbursementService());
	}

	@Test
	void testDeleteReimbursementService() throws ApplicationException {
		doNothing().when(this.reimbRepositoryDao).deleteById((Integer) any());
		assertTrue(this.reimbursementServiceImpl.deleteReimbursementService(123));
		verify(this.reimbRepositoryDao).deleteById((Integer) any());
		List<ReimbursementPojo> expectedAllResolvedReimbursementService = this.reimbursementServiceImpl
				.getAllPendingReimbursementService();
		assertEquals(expectedAllResolvedReimbursementService,
				this.reimbursementServiceImpl.getAllResolvedReimbursementService());
	}

	@Test
	void testRetrieveAReimbursementService() throws ApplicationException {
		when(this.reimbRepositoryDao.findById((Integer) any())).thenReturn(Optional.empty());
		assertNull(this.reimbursementServiceImpl.retrieveAReimbursementService(123));
		verify(this.reimbRepositoryDao).findById((Integer) any());
		List<ReimbursementPojo> expectedAllResolvedReimbursementService = this.reimbursementServiceImpl
				.getAllPendingReimbursementService();
		assertEquals(expectedAllResolvedReimbursementService,
				this.reimbursementServiceImpl.getAllResolvedReimbursementService());
	}

	@Test
	void testRetrieveAllReimbursementsService() throws ApplicationException {
		when(this.reimbRepositoryDao.findAll()).thenReturn(new ArrayList<>());
		List<ReimbursementPojo> actualRetrieveAllReimbursementsServiceResult = this.reimbursementServiceImpl
				.retrieveAllReimbursementsService();
		assertTrue(actualRetrieveAllReimbursementsServiceResult.isEmpty());
		verify(this.reimbRepositoryDao).findAll();
		assertEquals(actualRetrieveAllReimbursementsServiceResult,
				this.reimbursementServiceImpl.getAllPendingReimbursementService());
	}

	@AfterEach
	void tearDown() {
		System.out.println("@AfterEach - executed after each test method.");

	}

	@AfterAll
	static void done() {
		System.out.println("@AfterAll - executed after all test methods.");
	}

}
