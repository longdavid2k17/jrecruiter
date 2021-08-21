import {Company} from "./company";

export interface Requirement
{
  id:number;
  name:string;
  scaleIndicator:number;
  creationDate:Date;
}

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
  requirements:Requirement[];
}
