import { DocumentInfo } from './../shared/model/document';
import { Component, Input, OnInit } from "@angular/core";
import { DomSanitizer, SafeUrl} from '@angular/platform-browser';
import { ActivatedRoute, Router } from "@angular/router";
import { VentureDtls } from "./model/venture-dtls";
import { VentureService } from "./service/ventureservice.service";

@Component({
  selector: "venturedetails",
  templateUrl: "./venturedetails.component.html",
  styleUrls: ["./venturedetails.component.css"]
})
export class VenturedetailsComponent implements OnInit {
  @Input() ventureDtls: VentureDtls[];
  userId:number;
  url:SafeUrl ;
  constructor(
    private ventureService: VentureService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private sanitizer: DomSanitizer
  ) {
    this.activatedRoute.params.subscribe(params => {
      if (params["userId"]) {
        this.userId = params["userId"];
      }
    });
  }

  ngOnInit() {
    this.ventureService.getVentureDetails(this.userId).subscribe(
      res => {
        this.ventureDtls =res;
        if (this.ventureDtls===null || typeof this.ventureDtls  === 'undefined' || this.ventureDtls.length === 0) {
          this.router.navigate(["ownerconnect"], { queryParams: { message: "No venture(s) are linked, At least one plot should be linked with venture" }} );
        }
        this.url=this.sanitizer.bypassSecurityTrustResourceUrl("data:image/jpeg;base64,iVBORw0KGgoAAAANSUhEUgAAASoAAAEsCAYAAAB0Y/4yAAASfklEQVR4nO3df7BdVXXA8e9hMpkMgwylGeowlKGoFBFRU4ZSax1/0VpKrVqqIP6qdSpaRlE7olNLh7EttZaxaKfUWrQUEUEcpBR/R6EqYhWDQfkpIharCIQAMZCQZPWPfaMxebnvvvfuuWvfu7+fmTUvk5fMW2e/c9Y9Z5/9AyRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJmjZddgJSwDJgFXAY8BjgYGA/YE9gxeCfbQQ2AOuB7wI3AjcBN3Tle5phFiqlCDgceCFwDHAkPytIC7UZuAZYDXyqg/8ZT4aSmhSwMuCtATcHRE9xc8DbAw7MPl5JUyTgkIDzAzb1WKB2jq0BF0R5nJSkuQXsH/D+gEcmWKDmKlgfCzgouz0kVSRgj4DXBdyfWKB2jp9Eeexcnt0+kpIFHBjw5QoK0+7i+oBDs9tJUpKAYwPuraAYzRcPBrwku70kTVjAm6L0B2UXoYXEWZHdcJL6FyXeXUHRWWycH2XAqaRZNChS76ug2Cw1Lg3YI7s9JfUg4J0VFJlxxQWR3aDaLafQVChgL+CxwErKn/ekPJ6s3yHWdXBnYo6vB87O+vk9+bsO3padhHZloUoWZY7b04BnAkdTXp3vP+J/XwdcB3wDuBb4XAf39JHnjqLk+wVms2/nxR1cnJ2ElC5gRcAJAVcEPDTGx5etAasDTg54dE+5Pzrgrgoe1fqK+6Os3iC1KeCggHNiMiO2twZ8NMryKeM8hksrKCZ9x7Uxm3eL0u5FmZR7XuTNeft0wNPHcBwvqqCITCpOHcfvXqpewJ4BZ8ZkVw0YFhdGWZBuscfywwqOYVJxf4zeVyhNp4DnBtxRwQW3c9wX8OpY+PGcXkHuk47zezk5pGwBy2I6xhd9MmCfEY9pZZT5cdk5TzoeiTJMRJodAfsFfLGCC2zUuDngkBGO6+0V5JoV507i3JEmIsobvT6X2O0r7g947pDjWh5t9U3tHJuip6EeGp3zm8YgynK3X2aEu5MK7Q1cNqRYHU/bF+py4KXZSbTOQrVEUfowrmK63xAtp4yPetYc3ztx0slU6KTsBFrnFJoliDIX7yvMTofrRuCYDq4GiNLZfhcu3wvwxA6+lZ1Eq7yjWqQoF+8VzE6RgjL5+dId+mSOxSK13W778dQ/C9XinQUclZ1ED/YDLopybjwzO5mKPDs7gZb56LcIAc8HLs3Oo2d/S+lIn8YXBH3YAPxCB1uyE2mRhWqBAvYFbqV8VVue1MHa7CRa5KPfwp2JRapVbrOVxEK1AFH6pF6dnYfS+BicxEK1MGdim7XscdkJtMqLbkSDu6m5BkSqHT7yJ7FQje4vshNQur2yE2iVhWoEAQcCx2XnoXR7ZyfQKgvVaF6KbSVH6afx4hvNy7ITUBXWZyfQKgvVPAKOwPEzKh7OTqBVFqr5PSM7AVWj981dNTcL1fycjKrtvpedQKssVPNb8n54mhm3ZyfQKgvVEFFW7RxppxY1wYXzkliohnNul7bbAlyXnUSrLFTD+bZP263tylLNSmChGu6XsxNQNT6TnUDLLFTDObdL212RnUDLLFTDWagEZfzU1dlJtMxCNdyK7ARUhf/oYFt2Ei2zUEnze392Aq2zUA33QHYCSve5Dm7KTqJ1FqrhfB2tM7ITkIVqPv+bnYBS/XcHX8pOQhaq+Xw/OwGl2Qa8OTsJFRaq4W7JTkBp/q2Dr2cnocKdkocIWAY8iMMUWvMj4AkdrMtORIV3VEN0TkRt1SssUnVZlp3AFLgSODo7CU3MncBJAadRlvjZC9gMbBjEPcB3gBspXQNru/J99chHv3lEWTjvquw8KrMNWAvcQLlgv0u5A9lAGdKxjLK11D7AAcDjKUvmHMnsTUvaSHkzuBr4r660icbMQjWPQT/VXbhL7o+Bi4FPA1/qFrEjy6Atj6TsOH0icPhYM6zDN4APAh/pXGNdkxRwTkA0GpcHPC966CYIODzg3QEPVnCc446fDI5t/3G3mzSngKdWcOJPMrYGXBhlq7BJtO++AacH3FvBsY87NgWcHS5prUkIWFPBST+JuDbgqKQ2XhlwbgVt0Ef8MOAlGe2qhgS8vIKTvc/YFHBqVDBkJcod7K0VtEkfcXnY36m+BCwLuK2CE72PuC1KJ3c1AvYO+GgFbdNH3BEOeVFfAl5UwUk+7lgdZShBlQL+vII26iM2hY+C6kOU+EoFJ/m44mMBy7PbdT4Brwx4pIL26iNen92+mkFRXqlvquAEX2pcEBX0R40q4PlR3kZmt1sf8fbs9tUMCjijgpN7KbE6puBOamcBJ1fQdn3FydntqxkTsEfAVRWc3IuJ66PiPqn5BLyrgjbsI7YGPC+7fTVjAvYPuLuCE3wh8ZOY8t2fY7o/JOaLB6PMiZTGJ+CowcWffYKPGq/MbrNxiPIhMYuj2CPKwOKpeyxX5aLMg5uGN1KfzW6rcYrZHoB7dnb7agYFvDDqfhO4KWbskSJKfLGCtu0jtkZlA3A1I6LcWdX6GHhWdvv0IWBVBW3bV3wtpmj4iKZIwJFRJp9mn+Q7xkMBj85um75EmTuX3cZ9xauy21czKkpHb01vpc7JbpM+BRxdQRv3FbeFy4WrL1FeoZ8edfRbTfVwhFEEfLWCdu4rnA+ofgUcFrkdvl/LboNJCDilgoLSV3wzu33VgChxfMCNCSf5KdnHPwlRFtybhiEii41V2W2sRkRZz+qEKG9zJnWCH5R93JMSdfULjjscV6XJCzhvAif37dnHOUkx/RPFh8Vd0fhQBd8oTECUnUhWAY8FHgM8bQI/9soJ/IyarAZOz06iJ/tRzp+vZyeSxULVgyibbB4H/C6lKB2ckMa1CT8z03XZCfTsWTRcqDQmUeLYKOt8P1TB48JvZ7fJpAX8oIJ27ys+md2+mbyjWqIoM91fCbwBOCw3m59zU3YCCb7D7G74mbKFWS0sVIsU5cvxwJmUvqfa/Cg7gQR3ZifQo30DVra6TbyFahGijPb+IPVuebS5g83ZSSTYkJ1Azw6h0ULV9CvPhRr0Q50CrKHeIgWzf8HuzgPZCfRsppbqWQjvqEYUZZ3xC4Fjs3MZwZbsBJLM+nE3u8OyhWoEUYYXXE5dneXD7JWdQJKp3bRiRK3+Xi1U8wl4MmUZ35XZuSzAngF7dLAtO5EJm/VC9ajsBLLYRzVEwBFMX5HabhpzXqp9shPoWbObPliodiPKY95qpveCr3HIRN9mfe2tVl+SWKjmEqU4Xcb0Filo7A1RlG6Mg7Lz6Nn92QlksVDtZDDS/KNM/x3JE7MTmLBDmf0+1/XZCWSxUO3qDOAZ2UmMwdOzE5iwFo73+9kJZLFQ7SDgqcBbsvMYk1Ux+53LOzomO4EJaHH+JmCh+qmAFcD5zE6b7EFZGmTmDR7Xn5GdR8824x2VgDeRs25Un16WncCEPJfZv3tc2+C4uJ+yUAFRNug8LTuPHhwbbUy7eEV2AhNwZXYCmSxUxduYzVHNy4GXZyfRp8GHzHHZeUzA6uwEMnXZCWQb3HHcwezOo7oTeMysLvsScBblsX2WbQZ+sXPAZ9Nex+wWKYADgJdmJ9GHwcDcP83OYwI+0XKRgsYL1WALotdk5zEB74jZLMZnMJvHtbPzshPI1nShooybOiA7iQnYH/jL7CTGKcr2USdn5zEB64BPZCeRrfVCdWJ2AhP0poDDs5MYh8G8vnNo4/z9p1ntX1yIpjvTA37A7O5aMpebgF/rYGN2IksR8E5mZwbBMBuAX2l1Q4cdtfCJNKcok45bKlJQJu6ek53EUkQZ3NlCkQL4V4tU0Wyhoo1JrHN5ecBbs5NYjMGj60XZeUzIPcDfZCdRi5YL1W9mJ5DozICXZCexEFFeenyS2RyYO5fTutKRLtouVLO+GuR8zospGV812FzjC7TxhhbgauAD2UnUpOVCNWsTkBdqGXB+wKnZiQwzWLf+y0z/QoajegA4qem3XCoC9g4I46fx3qhw44CA4wPur6B9JhnHZ7e7KhFwYAUnZG2xJiq5awlYHqV4ZrfJpOPs7LZXRQIOr+CkrDEeCnhrJN5dBTwn4NYK2mLScWG03RWjnQUcXcGJWXPcGPD8mOzv5NCAiyo49oxYHRU+etek1Qo+67uVLNWhwKXA9QEn9HkRBRwZZdefbwMv6uvnVOxTwO87TWa4Jl8uRHmT9M3sPKbIOuDDwIXANUtdEjfKG9fjKStzHrb09KbWh4FXdLAlO5HatVqoDgZuy85jSj1AWRb3KuAG4Bbge7srXlHWjDp0EL9B2XDioEkkWrFtwF8Bf93kBbgITbZTwH7AXdl5zJAtlAm06wZf99oh9kzMq0Y/Ak7sGl8DfaFaLVQAD9LGomuqx4eAN3fw4+xEpk2TnemD6vyd3CzUkBuAZ3bwMovU4jRZqAaa3XVWE3MTZW/FJ/motzQtFyrf+qkP2yhLB/8h8IQOPuRbvaVreTzRldkJKM03gM8Az2M8wyMeBq4BLgM+0pUOc41Rk53pwPZ1t+/DDvUWbQEe18H3oqzyejTw65SF+Q6mDJ9YsZv/+wBlSMYtlEGq1wBXd6VYqSfNFiqAgMtpY5dd7eqfO/iz3X0zygJ9KygfZFsoBWp90xdMoqbbPcoqlxdk56EUD1M2TvAxbQq03JkO8HEa34G2YSuAV2UnodE0XagG20Z9PDsPpfmj7AQ0mqYf/QACngysyc5DaR7XOfi3ek3fUQF0cB1umd0yl/6dAs0XqgH3T2vX72UnoPk1/+i33WDxNj9d27MReNRS19hSvyxUA1H2jLsRB4C26PGdcz+r5qPfQAd3Au/IzkMpVmUnoOEsVD/vH4DPZyfRM9fm3tVTshPQcBaqHQz6KU5idtcM+jHwnuwkKrRfdgIazkK1k8GUihcze3cem4EXZCdRqX2yE9BwFqo5DBY5++PsPMbstR1cjRflXGyTylmodqMrWxmdlp3HmLyxgw8M/uxmC7vaNzsBDdfywnnz6uDvozwyvTs7lyV4bQf/kp1E5dyluHLeUc2jg3+krHs9bcvJbgH+ZI4itTEjmcq5gkblLFQj6Mo2R8cwPWsX/R9l15MPzPE9L8pdPZCdgIazUI1o0MH+FOpfa/1Kyq4nX9rN9y1Uu7JQVc5CtQCDoQvPBt5IfRf8euANwLM7uGfIv7t9QvlMk+9nJ6DhLFQL1MG2Qb/VrwKXZOcz8CHKfLX3jDC51jltu7oxOwGpVwGrAi4LiAnH1oCPRdlBZSH5rkzItfZ4Tl/nh1SVgCMC3hdwX88X1YMB7w147BJy/UEFxaGmcAqN2hKwPOD4gIsC7h7ThXR3wLkBx8Xu95tbSI4XVFAcaolvj+P3rn454HPMujJA9BLgkih/dQTwdOAJwCHAoZRNL+eykdIpfgtlieQ1wFpg7ZgXdvsCZaswzf5qGTPBhfOSRFmgby/KlJYNlM0tJzIROspOwLdP4mdNgT/o4D+zk9BwFqpGBXyFBXbEz6D1wC9N6gNCi+fwhHadl51ABS62SE0H76gaFWXFgB/S9oTc3xoygl8V8Y6qUR2sA/49O49EX7dITQ/vqBoWcDBwM22+/X1BBx/PTkKj8Y6qYR18F7g4O48E38IiNVW8o2pcwIGUuW4trfz5Ox18JjsJjc47qsZ1ZeWAlvYzvMQiNX28oxJR3vytAQ7LzqVnGyirTNyZnYgWxjsqbZ/2cxLwcHYuPXuNRUqacgGvq2CScF9xbnb7ShqTgPMrKCrjjmujrZcF0myLskzN6gqKy7ji1oCV2e0qacwC9g74agVFZqlxdyxhgUFJlQvYJ+CLFRSbxcYdUdb+kjTLAlYEXF5B0VlofDvggOz2kzQhAcsC3lVB8Rk1roiyMoSk1gQcG3BvBYVod/FIwKmR3VCScgUcEHBpBUVp57g24Mjs9pFUkcHd1W0VFKj7Ak4JZ1hImkuU8VavSipY9wacHrBPdjtImgJROttPCPhslN2b+yxQ10fph9or+7glTakofVhviTL+atOYitM3o7x1PCL7+JTHZV7Uiyhz654GHMXPNl89hN3fDW2mrDh60yDWAJ/v4J7+s1XtLFSaqEHH996DWEbZW2+D21ZJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJWpD/B+Ao/tdDLF0LAAAAAElFTkSuQmCC");
      },
      error => {
        console.log(error._body);
      }
    );
  }
  createImageFromBlob(image: string):SafeUrl {
    return this.sanitizer.bypassSecurityTrustResourceUrl(image);
 }
}
