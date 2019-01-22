public class Err {

	public static void checkValidOsloMetMail(String email) throws InvalidEmailException {
	    String[] splitStrAlpha = email.split("@");
	    if (splitStrAlpha.length != 2) {
	        throw new InvalidEmailException("msg"); // ERROR CODE 1: string is not a valid email
	    }

	    if(!splitStrAlpha[1].equals("oslomet.no")) {
	        throw new InvalidOsloMetEmailException
	        	(email + " is not a valid OsloMet email"); // ERROR CODE 2: string is not a valid OsloMet email
	    }

	    String studentStr = splitStrAlpha[0];
	    if(studentStr.length() != 7 || studentStr.charAt(0) != 's') {
	        throw new InvalidStudentOsloMetEmailException
	        	(email + " is not a valid OsloMet student email"); // ERROR CODE 3: email is not a valid student OsloMet email
	    }

	    // check if the six numbers behind "s" are valid numbers
	    try {
	        int testInt = Integer.parseInt(studentStr.substring(1));
	        if(testInt < 0)
	            throw new NumberFormatException
	            	("no negative numbers in student numbers"); // no negative numbers in student numbers
	    } catch(NumberFormatException e) {
	        throw new NumberFormatException();
	    }
	     // email is valid
	}

	public static void checkMail(String email) {
	    try {
	    checkValidOsloMetMail(email);
		} catch(InvalidStudentOsloMetEmailException e) {
			System.err.println(email + " is not a valid OsloMet student email");
		} catch(InvalidOsloMetEmailException e) {
			System.err.println(e.getMessage());
			//System.err.println(email + " is not a valid OsloMet email");
		} catch(InvalidEmailException e) {
			System.err.println(email + " is not a valid email");
		} //catch(NumberFormatException e) {
			//System.out.println("Nein, du macht nicht!!!");
		//}
		
		

		System.out.println("Hello student with id " + email.split("@")[0]);
		/*
	    if(testMail == -1) {
	        System.err.println(email + " is not a valid email");
	    } else if (testMail == -2) {
	        System.err.println(email + " is not a valid OsloMet email");
	    } else if (testMail == -3) {
	        System.err.println(email + " is not a valid OsloMet student email");
	    }
	    else {
	        System.out.println("Hello student with id " + email.split("@")[0]);
	    }
	    */
	
}

	public static void main(String[] args) {
		String email1 = "s123456@hioa.no";
		String email2 = "s123456@oslomet.no";
		String email3 = "henrik.lieng@oslomet.no";
		String email4 = "so_bad!@oslomet.no";
		String email5 = "thisIsNoEMail";

		checkMail(email1);
		checkMail(email2);
		checkMail(email3);
		checkMail(email4);
		checkMail(email5);
	}
}

	class InvalidEmailException extends Exception {
		public InvalidEmailException(String msg) {
			super(msg);
		}
	}

	class InvalidOsloMetEmailException extends InvalidEmailException {
		public InvalidOsloMetEmailException(String msg) {
			super(msg);
		}
	}

	class InvalidStudentOsloMetEmailException extends InvalidOsloMetEmailException {
		public InvalidStudentOsloMetEmailException(String msg) {
			super(msg);
		}
	}
