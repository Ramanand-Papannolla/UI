import { SocialUser } from 'angularx-social-login';

export class User extends SocialUser {
    // id?: number;
    // userName: string;
    // firstName?:string;
    // lastName?:string;
    // emailId?:string;
    password?:string;
    role?:string[];
    connected?:boolean;
    userName:string;
}
