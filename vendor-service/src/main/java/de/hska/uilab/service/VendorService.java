package de.hska.uilab.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.hska.uilab.data.Vendor;

@RestController
public class VendorService {

	private final static Logger LOGGER = Logger.getLogger(VendorService.class.getName());

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<String> showMessage() {
		LOGGER.log(Level.INFO, "Helloooooo");
		return new ResponseEntity<>("Helloooooo", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vendors/{tenantId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Vendor>> getVendorsByTenantId(@PathVariable String tenantId) {
		LOGGER.log(Level.INFO, "Get vendors by tenantId " + tenantId);
		return new ResponseEntity<List<Vendor>>(new MockService().getVendorList(5), HttpStatus.OK);
	}

	@RequestMapping(value = "/vendors/{tenantId}/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Vendor> getVendorByTenantIdAndId(@PathVariable String tenantId, @PathVariable String id) {
		LOGGER.log(Level.INFO, "Get vendor by tenantId " + tenantId + " and id " + id);
		return new ResponseEntity<Vendor>(new MockService().getVendor(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/vendors/{tenantId}", method = RequestMethod.POST)
	public ResponseEntity createVendorForTenant(@RequestBody Vendor vendor, @PathVariable String tenantId) {
		LOGGER.log(Level.INFO, "Create vendor " + vendor.getName() + " for tenant " + tenantId);
		MockService ms = new MockService();
		if (ms.createVendorForTenant(vendor, tenantId)) {
			LOGGER.log(Level.INFO, "Created vendor.");
			return new ResponseEntity(HttpStatus.CREATED);
		} else {
			LOGGER.log(Level.INFO, "Couldn't create vendor.");
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(value = "/vendors/{tenantId}/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Vendor> modifyVendorForTenant(@RequestBody Vendor vendor, @PathVariable String tenantId,
			String id) {
		LOGGER.log(Level.INFO, "Modify vendor " + id + " for tenant " + tenantId);
		MockService ms = new MockService();
		Vendor updatedVendor = ms.modifyVendorForTenant(id, vendor, tenantId);
		if (updatedVendor != null) {
			LOGGER.log(Level.INFO, "Modified vendor.");
			return new ResponseEntity<>(updatedVendor, HttpStatus.OK);
		} else {
			LOGGER.log(Level.INFO, "Couldn't modify vendor.");
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(value = "/vendors/{tenantId}/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteVendorForTenant(@PathVariable String tenantId, @PathVariable String id) {
		LOGGER.log(Level.INFO, "Delete vendor " + id + " for tenant " + tenantId);
		MockService ms = new MockService();
		if (ms.deleteVendorForTenant(id, tenantId)) {
			LOGGER.log(Level.INFO, "Deleted vendor.");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			LOGGER.log(Level.INFO, "Couldn't delete vendor.");
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
