import { Byte } from '@angular/compiler/src/util';
import { SafeUrl, SafeValue } from '@angular/platform-browser';

export class Reimbursement {
   reimbId      : number = 0;
   reimbDate    : string = '';
   reimbReason  : string = '';
   reimbAmount  : number = 0;
   reimbStatus  : string = '';
   reimbRemoved : boolean = false;
   userId       : number = 0;
   reimbImage   : SafeUrl = "";
}