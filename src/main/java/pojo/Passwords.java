package pojo;

public class Passwords {
	private NonMatching nonMatching;
	private LessThanSix lessThanSix;
	
	public NonMatching getNonMatching() {
		return nonMatching;
	}
	public void setNonMatching(NonMatching nonMatching) {
		this.nonMatching = nonMatching;
	}
	public LessThanSix getLessThanSix() {
		return lessThanSix;
	}
	public void setLessThanSix(LessThanSix lessThanSix) {
		this.lessThanSix = lessThanSix;
	}

	public class NonMatching{
		private String password;
		private String confirmPassword;
		
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getConfirmPassword() {
			return confirmPassword;
		}
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
	}
	
	public class LessThanSix{
		private String password;
		private String confirmPassword;
		
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getConfirmPassword() {
			return confirmPassword;
		}
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
	}
}
