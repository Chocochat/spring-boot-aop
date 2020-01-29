package com.sing.aspect;

import java.lang.annotation.*;

/**
 *  Used to mark methods that needs to perform the user safety flag check.
 *
 *  Example 01: Simple key
 *
 *  @UserSafeGuardedMethod(key="accountId")
 *  public Account getAccount(String accountId) {
 *     return accountService.getAccount(accountId);
 *  }
 *
 *  Example 02: Complex key
 *
 *  class AccountRequest {
 *     String accountId;
 *     // getter / setter
 *  }
 *
 *  @UserSafeGuardedMethod(key="request.accountId")
 *  public Account getAccount(AccountRequest request) {
 *     return accountService.getAccount(request.getAccountId());
 *  }
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface UserSafetyAgentPortalGuard {
    String key();

}
