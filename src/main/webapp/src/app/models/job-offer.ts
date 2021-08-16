import {Company} from "./company";

export interface JobOffer
{
  id:number;
  positionTitle:string;
  positionDescription:string;
  lowEndPaymentRange:number;
  highEndPaymentRange:number;
  contractType:string;
  leadingTechnology:string;
  creationDate:Date;
  company:Company;
}
