import { Message } from './../shared/model/message';
import { Component, OnInit } from "@angular/core";
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl
} from "@angular/forms";
import { Router, ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs";
import { map, startWith } from "rxjs/operators";
import { District } from "./../shared/model/district";
import { Mandal } from "./../shared/model/mandal";
import { Plot } from "./../shared/model/plot";
import { State } from "./../shared/model/state";
import { Survey } from "./../shared/model/survey";
import { Village } from "./../shared/model/village";
import { PlotOwnerConnectService } from "./../shared/service/plot-owner-connect.service";
import { MatSnackBar } from "@angular/material";

@Component({
  selector: "app-plot-owner-connect",
  templateUrl: "./plot-owner-connect.component.html",
  styleUrls: ["./plot-owner-connect.component.css"]
})
export class PlotOwnerConnectComponent implements OnInit {
  platownerconnectForm: FormGroup;
  surveyForm: FormGroup;
  plotForm: FormGroup;

  states: State[];
  districts: District[];
  mandals: Mandal[];
  villages: Village[];

  plots: Plot[] = [];
  surveys: Survey[] = [];
  loading = false;
  submitted = false;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private plotOwnerConnectService: PlotOwnerConnectService,
    public snackBar: MatSnackBar
  ) {}

  openSnackBar(message: string, action: string) {
    if (null !== message && undefined !== message && "" !== message) {
      this.snackBar.open(message, "X", {
        duration: 20000,
        verticalPosition: "top"
        // panelClass: "notif-success"
      });
    }
  }
  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.openSnackBar(params["message"], null);
    });

    this.surveyForm = this.formBuilder.group({
      id: "",
      name: "",
      surveyNum: "",
      document: ""
    });
    this.plotForm = this.formBuilder.group({
      id: "",
      name: "",
      plotNumber: "",
      document: ""
    });
    this.platownerconnectForm = this.formBuilder.group({
      stateId: ["", Validators.required],
      districtId: ["", Validators.required],
      mandalId: ["", Validators.required],
      villageId: ["", Validators.required],
      surveyNumber: ["", Validators.required],
      surveyDoc: null,
      plotNumber: ["", Validators.required],
      plotDoc: null
    });
    this.plotOwnerConnectService.getStates().subscribe((data: State[]) => {
      this.states = data;
    });
    this.platownerconnectForm
      .get("surveyNumber")
      .valueChanges.subscribe(val => {
        if (
          null !== val &&
          val != undefined &&
          val.id != "" &&
          val.id != undefined
        ) {
          this.getPlotsBySurveyId(val.id);
        }
      });
  }

  onFileChange(event) {

    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      if (event.target.files && event.target.files[0]) {
        let reader = new FileReader();
        let file = event.target.files[0];
        // reader.readAsText(file, "UTF-8");
        reader.readAsDataURL(file);
        var document = {
          title: file.name,
          type: file.type,
          image: ""
        };
        reader.onload = () => {
          document.image= reader.result;
        };
        reader.onerror = error => {
          console.log(error);
        };

       this.surveyForm.get("document").setValue(new FormControl(document));
        this.platownerconnectForm.get("surveyDoc").setValue(document);
      }
    }
  }

  save() {
    this.submitted = true;
    this.platownerconnectForm.addControl(
      "user",
      new FormControl(
        JSON.parse(sessionStorage.getItem("user")),
        Validators.required
      )
    );
    if (this.platownerconnectForm.invalid) {
      return;
    }
    this.loading = true;

    this.plotOwnerConnectService
      .save(this.platownerconnectForm.value)
      .subscribe(
        data => {
          this.platownerconnectForm.reset(data);
          this.loading = false;

          this.openSnackBar("uploaded is successful",null);
        },
        error => {
          this.loading = false;
          console.log(error.Message);
          console.log(error.error);
          this.openSnackBar(error.error.error,null);
        }
      );
  }
  reset(stateInfo) {
    //this.stateName = '';
    //this.districtName = '';
    // this.getDistrictsByStateId(stateInfo.stateName);
  }

  getDistrictsByStateId(stateId: number) {
    this.resetDistricts();
    this.plotOwnerConnectService
      .getDistricts(stateId)
      .subscribe((data: District[]) => {
        this.districts = data;
      });

    // this.districtName="";
    // let dropDownData = this.stateList.find((state: any) => state.stateName === stateId); // Gets one state object
    // if (dropDownData) {
    //   this.districtList = dropDownData.districtList;
    //   if(this.districtList){
    //     this.districtName=this.districtList[0];
    //   }
    // } else {
    //   this.districtList = [];
    // }
  }
  getMandalsByDistrictId(districtId: number) {
    this.resetMandals();
    this.plotOwnerConnectService
      .getMandals(districtId)
      .subscribe((data: Mandal[]) => {
        this.mandals = data;
      });
  }
  getVillagesByMandalId(mandalId: number) {
    this.plotOwnerConnectService
      .getVillages(mandalId)
      .subscribe((data: Village[]) => {
        this.villages = data;
      });
  }
  getsurveysByVillageId(villageId: number) {
    this.plotOwnerConnectService
      .getSurveys(villageId)
      .subscribe((data: Survey[]) => {
        this.surveys = data;
      });
  }

  getPlotsBySurveyId(surveyId: number) {
    this.plotOwnerConnectService
      .getPlots(surveyId)
      .subscribe((data: Plot[]) => {
        this.plots = data;
      });
  }

  displaySurveyFn(survey?: Survey): string | undefined {
    return survey ? survey.surveyNum : undefined;
  }
  displayPlotFn(plot?: Plot): string | undefined {
    return plot ? plot.plotNumber : undefined;
  }
  resetDistricts() {
    this.districts = [];
    this.resetMandals();
  }
  resetMandals() {
    this.mandals = [];
    this.resetVillages();
  }
  resetVillages() {
    this.villages = [];
  }
}
