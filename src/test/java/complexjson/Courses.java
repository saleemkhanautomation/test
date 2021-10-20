package complexjson;

import java.util.List;

public class Courses {

	private List<WebAutomation> webautomation;
	private List<ApiAutomation> apiautomation;
	private List<MobileAutomation> mobileautomation;
	public List<WebAutomation> getWebautomation() {
		return webautomation;
	}
	public void setWebautomation(List<WebAutomation> webautomation) {
		this.webautomation = webautomation;
	}
	public List<ApiAutomation> getApiautomation() {
		return apiautomation;
	}
	public void setApiautomation(List<ApiAutomation> apiautomation) {
		this.apiautomation = apiautomation;
	}
	public List<MobileAutomation> getMobileautomation() {
		return mobileautomation;
	}
	public void setMobileautomation(List<MobileAutomation> mobileautomation) {
		this.mobileautomation = mobileautomation;
	}
	
}
