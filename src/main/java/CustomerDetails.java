import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties({ "custSal" })
public class CustomerDetails {

	private int custId;
	private String custName;
	private String custDesig;
	private int custSal;

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@JsonIgnore
	public String getCustDesig() {
		return custDesig;
	}

	public void setCustDesig(String custDesig) {
		this.custDesig = custDesig;
	}

	public int getCustSal() {
		return custSal;
	}

	public void setCustSal(int custSal) {
		this.custSal = custSal;
	}

	public static void main(String[] args) {
		/*
		 * CustomerDetails customerDetails = new CustomerDetails();
		 * customerDetails.setCustId(1); customerDetails.setCustName("Muhammed Younus");
		 * customerDetails.setCustDesig("Software Developer");
		 * customerDetails.setCustSal(1111);
		 * 
		 * ObjectMapper mapper = new ObjectMapper(); try { Path path =
		 * Paths.get("payload.json"); String json =
		 * mapper.writeValueAsString(customerDetails);
		 * 
		 * List<CustomerDetails> customerList = mapper.readValue(json, new
		 * TypeReference<List<CustomerDetails>>(){});
		 * 
		 * 
		 * //Files.write(path, mapper.writeValueAsString(customerDetails).getBytes());
		 * 
		 * 
		 * 
		 * } catch (JsonProcessingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
	}
}
