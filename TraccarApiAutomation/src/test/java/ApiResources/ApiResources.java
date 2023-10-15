package ApiResources;

public enum ApiResources {

	loginApi("/users/login"),
	currentApi("/users/current"),
	baseCurrenyApi("/currency/baseCurrency"),
	poTypeApi("/potypes"),
	getUsersApi("/users");
	
	private String resource;
	ApiResources(String resource){
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
}
