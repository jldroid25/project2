import { SafeUrl } from '@angular/platform-browser';

export class ReimbImage {
    reimbId      : number = 0;
   reimbDate     : string = '';
   reimbReason   : string = '';
   reimbAmount   : number = 0;
   reimbStatus   : string = 'Pending';
   reimbRemoved  : boolean = false;
   userId        : number = 0;
   fileUrl       : SafeUrl = "";

}