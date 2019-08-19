import { User } from './user';

export interface Message {
  id?: number;
  channel: string;
  sender: User;
  receiver: User;
  messageBody: string;
  sentTime?: number;
  readTime?: number;
  deliveryTime?:number;
  deliveryStatus?:boolean;
}
