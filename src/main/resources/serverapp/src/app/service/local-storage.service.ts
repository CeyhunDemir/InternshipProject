import { Injectable } from '@angular/core';
import { TransferState, makeStateKey } from '@angular/core';

const TOKEN_KEY = makeStateKey<string>('token');

@Injectable({
  providedIn: 'root',
})
export class LocalStorageService {
  constructor(private transferState: TransferState) {}

  setItem(key: string, value: string): void {
    if (typeof window !== 'undefined') {
      localStorage.setItem(key, value);
    } else {
      this.transferState.set(makeStateKey<string>(key), value);
    }
  }

  getItem(key: string): string | null {
    if (typeof window !== 'undefined') {
      return localStorage.getItem(key);
    } else {
      return this.transferState.get(makeStateKey<string>(key), null);
    }
  }
  removeItem(key: string): void {
    if (typeof window !== 'undefined') {
      localStorage.removeItem(key);
    } else {
      this.transferState.remove(makeStateKey<string>(key));
    }
  }
}
  