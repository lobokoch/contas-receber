/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.789
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { AuthService } from './auth.service';
import { HttpClientWithToken } from './http-client-token';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  tokensRevokeUrl = environment.apiUrl + '/tokens/revoke';

  constructor(
    private http: HttpClientWithToken,
    private auth: AuthService
  ) { }

  logout() {
    return this.http.delete(this.tokensRevokeUrl, { withCredentials: true })
      .toPromise()
      .then(() => {
        this.auth.cleanAccessToken();
      });
  }

}
