package presentation;

import java.util.List;

import exception.ApplicationException;
import io.javalin.Javalin;
import pojo.ErrorPojo;
import pojo.ReimbursementPojo;
import pojo.UserPojo;
import service.UserService;
import service.UserServiceImpl;
import service.ReimbursementService;
import service.ReimbursementServiceImpl;

public class ReimburseMain {

	ErrorPojo error = new ErrorPojo();

	public static void main(String[] args) {

		ReimbursementService reimbursementService = new ReimbursementServiceImpl();
		UserService authUserService = new UserServiceImpl();

		Javalin app = Javalin.create((config) -> config.enableCorsForAllOrigins()).start(4041);
		app.get("/", ctx -> ctx.result("Reimbursment app endpoints have been requested"));

		// ---------- ALL Reimbursement API Endpoints ------------//

		// http://localhost:4041/api/reimbursements
		app.get("api/reimbursements", (ctx) -> {
			ctx.json(reimbursementService.retrieveAllReimbursementsService());
		});

		// The Get endpoint to fetch 1 reimbursements
		// http://localhost:4041/api/reimbursements/2
		app.get("api/reimbursements/{rid}", (ctx) -> {
			ctx.json(reimbursementService.retrieveAReimbursementService(Integer.parseInt(ctx.pathParam("rid"))));
		});

		// API Call endpoint To delete reimbursements
		// http://localhost:4041/api/reimbursements/2
		app.delete("api/reimbursements/{rid}", (ctx) -> {
			reimbursementService.deleteReimbursementService(Integer.parseInt(ctx.pathParam("rid")));
		});

		// APi call to to do post endEnpoint
		// http://localhost:4041/api//reimbursements
		app.post("api/reimbursements", (ctx) -> {
			ReimbursementPojo returnReimbPojo = reimbursementService
					.createReimbursementService(ctx.bodyAsClass(ReimbursementPojo.class));
			ctx.json(returnReimbPojo);
		});

		// Api call endpoint to update -- put
		// http://localhost:4041/api/reimbursements/5
		app.put("api/reimbursements/{rid}", (ctx) -> {
			ReimbursementPojo returnReimbPojo = reimbursementService
					.updateReimbursementService(ctx.bodyAsClass(ReimbursementPojo.class));
			ctx.json(returnReimbPojo);
		});

		// ---------end points for Specific Angular Reimbursement components

		// For returning all Resolved reimbursements
		// http://localhost:4041/api/reimbursementsresolved

		app.get("api/reimbursementsresolved", (ctx) -> {
			ctx.json(reimbursementService.getAllResolvedReimbursementService());
		});

		// For returning all Pending reimbursements
		// http://localhost:4041/api/reimbursementspending
		app.get("api/reimbursementspending", (ctx) -> {
			ctx.json(reimbursementService.getAllPendingReimbursementService());
		});

		// For returning a reimb based on user_id
		// http://localhost:4041/api/reimbursementsuser/
		app.get("api/reimbursementsuser/{rid}", (ctx) -> {
			ctx.json(reimbursementService.getAUserReimbursementService(Integer.parseInt(ctx.pathParam("rid"))));
		});

		// For returning a Pending reimb based on user_id
		// http://localhost:4041/api/reimbursementspending/
		app.get("api/reimbursementspending/{rid}", (ctx) -> {
			ctx.json(reimbursementService.getAUserPendingReimbursementService(Integer.parseInt(ctx.pathParam("rid"))));
		});

		// For returning a Resolved reimb based on user_id
		// http://localhost:4041/api/reimbursementsresolved/
		app.get("api/reimbursementsresolved/{rid}", (ctx) -> {
			ctx.json(reimbursementService.getAUserResolvedReimbursementService(Integer.parseInt(ctx.pathParam("rid"))));
		});

		// ---------- ALL UserRegistration Login API Endpoints ------------//

		// The Get endpoint to fetch site users
		// http://localhost:4041/api/registerusers/
		app.post("api/registerusers", (ctx) -> {
			UserPojo returnUserInfoPojo = authUserService.registerUser(ctx.bodyAsClass(UserPojo.class));
			ctx.json(returnUserInfoPojo);
		});

		// The Get endpoint to fetch 1 user
		// http://localhost:4041/api/registerusers/2
		app.get("api/registerusers/{uid}", (ctx) -> {
			ctx.json(authUserService.retrieveAUser(Integer.parseInt(ctx.pathParam("uid"))));
		});

		// The Get endpoint to fetch all users
		// http://localhost:4041/api/registerusers
		app.get("api/registerusers", (ctx) -> {
			ctx.json(authUserService.retrieveAllUsers());
		});

		// The Get endpoint to update user
		// http://localhost:4041/api/registerusers
		app.put("api/registerusers/{uid}", (ctx) -> {
			UserPojo returnUserInformationPojo = authUserService.updateUser(ctx.bodyAsClass(UserPojo.class));
			ctx.json(returnUserInformationPojo);
		});

		// The Get endpoint to delete
		// http://localhost:4041/api/registerusers
		app.delete("api/registerusers/{uid}", (ctx) -> {
			authUserService.deleteUser(Integer.parseInt(ctx.pathParam("uid")));
		});
		
		/*

		app.exception(ApplicationException.class, (ae, ctx) -> {
			ErrorPojo error = new ErrorPojo();
			error.setErrorMessage(ae.getMessage());
			ctx.json(error).status(500);
		});
		*/

	}// main

}// class
