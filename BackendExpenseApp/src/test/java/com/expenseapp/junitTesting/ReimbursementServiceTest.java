package com.expenseapp.junitTesting;

;

public class ReimbursementServiceTest {

	/*

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

	 */

}
