import { Survey } from './survey';
import { Plot } from './plot';
import { User } from './user';
import { DocumentInfo } from './document';

export interface UserPlotInfo {
  user:User
  stateId:number
  districtId:number
  mandalId:number
  villageId:number
  surveyNumber:Survey
  surveyDoc:DocumentInfo
  plotNumber:Plot
  plotDoc:DocumentInfo
}
