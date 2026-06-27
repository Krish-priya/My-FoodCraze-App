package com.food.utility;

import java.util.Scanner;

import com.food.DAOImpl.UserDAOImpl;
import com.food.model.UserModel;

public class Launch {
	public static void main(String[] args) {
	try (Scanner s = new Scanner(System.in)) {
		System.out.println("ID: ");
		int id=s.nextInt();
		System.out.println("Name: ");
		String name=s.nextLine();
		System.out.println("Email: ");
		String email=s.nextLine();
		System.out.println("Password: ");
		String password=s.nextLine();
		System.out.println("Phone: ");
		String phone=s.nextLine();
		System.out.println("Address: ");
		String address=s.nextLine();
		UserModel u=new UserModel(id,name,email,phone,address,password);
		UserDAOImpl userDAO=new UserDAOImpl();
		userDAO.addUser(u);
		System.out.println(u);
	}
	}
	
}
