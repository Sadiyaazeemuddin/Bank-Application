package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.Repository.AccountRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/main/v1/")
public class AccountController {

    @Autowired
    private AccountRepository accountrepo;

    @GetMapping("/accounts")
    public List<Account> getAllAccount() {
        return accountrepo.findAll();
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account) {
    	 System.out.println("Received account: " + account.getAccountName());
        return accountrepo.save(account);
    }
    
    
    @DeleteMapping("/accounts/{id}")
    public  Account deleteAccount(@PathVariable Long id){
    	Account accounts=accountrepo.findById(id).
    			 orElseThrow(()->new ResourceNotFoundException("account not exist with id:"+id));
    	accountrepo.delete( accounts);
    	return  accounts;	
	
      }
    
	@GetMapping("/accounts/{id}")
	public ResponseEntity<Account> getEmployeeById(@PathVariable Long id) {
		Account accounts=accountrepo.findById(id).
   			 orElseThrow(()->new ResourceNotFoundException("account not exist with id:"+id));
		System.out.println("id is"+id);
	 return ResponseEntity.ok(accounts);
	}
    
    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> depositAccount(@PathVariable Long id,@RequestBody Account accountdetails) {
      Account accounts=accountrepo.findById(id).
    		  orElseThrow(()->new ResourceNotFoundException("account not exist with id:"+id));
  accounts.setAccountName(accountdetails.getAccountName());
  accounts.setAccountNumber(accounts.getAccountNumber());
  accounts.setBalance(accountdetails.getBalance());
  Account acc=accountrepo.save(accounts);
  return ResponseEntity.ok(acc);
    	
    }
    
    @PutMapping("/account/{id}")
    public  ResponseEntity <Account> withdrawAccount(@PathVariable Long id,@RequestBody Account accountwith) {
    	 Account accounts=accountrepo.findById(id).
       		  orElseThrow(()->new ResourceNotFoundException("account not exist with id:"+id));
    	 accounts.setAccountNumber(accountwith.getAccountNumber());
    	accounts.setBalance(accountwith.getBalance());
	      	  Account acc=accountrepo.save(accounts);
    	  return ResponseEntity.ok(acc);
    }
   
    @PutMapping("/accounts/transfer/{id}")
    public ResponseEntity<?> transferAmount(@PathVariable Long id, @RequestBody Account accountTransfer) {
        Account sourceAccount = accountrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not exist with id: " + id));
        double transferAmount = accountTransfer.getTransferamount();
        double currentBalance = sourceAccount.getBalance();
        if (currentBalance < transferAmount) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Insufficient balance in account : " + id));
        }
        Account destinationAccount = accountrepo.findByAccountNumber(accountTransfer.getAccountNumberto())
                .orElseThrow(() -> new ResourceNotFoundException(" accounttransfer cannot happen with account number: " + accountTransfer.getAccountNumberto()));
        
        if (sourceAccount.getAccountNumber().equals(accountTransfer.getAccountNumberto())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Cannot transfer to the same account: " + accountTransfer.getAccountNumberto()));
        }
        
        if (destinationAccount == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", " Accountnumber  not found: " + accountTransfer.getAccountNumberto()));
        }
        sourceAccount.setBalance(currentBalance - transferAmount);
        destinationAccount.setBalance(destinationAccount.getBalance() + transferAmount);
        accountrepo.save(sourceAccount);
        accountrepo.save(destinationAccount);
        return ResponseEntity.ok(Map.of("message", "Transfer successful to  account number: " + accountTransfer.getAccountNumberto()));
    }


    
}
    



    




