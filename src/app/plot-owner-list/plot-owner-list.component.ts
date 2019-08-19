import { UserPlotInfo } from "./../shared/model/userPlotInfo";
import { PlotOwnerConnectService } from "./../shared/service/plot-owner-connect.service";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { OwnerListDtls } from "./owner-list-dtls";

// const ELEMENT_DATA: OwnerListDtls[] = [
  // { plotNumber: '1', ownerName: 'Ramanand', surveyNumber: '22A', mobileNumber: '8983290578', documentId: 'DOC1' },
  // { plotNumber: '2', ownerName: 'Ismail', surveyNumber: '100C', mobileNumber: '8983290578', documentId: 'DOC2' },
  // { plotNumber: '3', ownerName: 'Jhon', surveyNumber: '22A', mobileNumber: '8983290578', documentId: 'DOC3' },
  // { plotNumber: '4', ownerName: 'Beryllium', surveyNumber: '201D', mobileNumber: '8983290578', documentId: 'DOC4' },
  // { plotNumber: '5', ownerName: 'Boron', surveyNumber: '88B', mobileNumber: '8983290578', documentId: 'DOC5' },
  // { plotNumber: '6', ownerName: 'Chris', surveyNumber: '00A1', mobileNumber: '8983290578', documentId: 'DOC6' },
  // { plotNumber: '7', ownerName: 'Warn', surveyNumber: '100C', mobileNumber: '8983290578', documentId: 'DOC7' },
  // { plotNumber: '8', ownerName: 'Sachin', surveyNumber: '201D', mobileNumber: '8983290578', documentId: 'DOC8' },
  // { plotNumber: '9', ownerName: 'Warrier', surveyNumber: '201D', mobileNumber: '8983290578', documentId: 'DOC9' },
  // { plotNumber: '10', ownerName: 'Bolein', surveyNumber: '22A', mobileNumber: '8983290578', documentId: 'DOC10' },
  // { plotNumber: '11', ownerName: 'Ramanand', surveyNumber: '22A', mobileNumber: '8983290578', documentId: 'DOC1' },
  // { plotNumber: '12', ownerName: 'Ismail', surveyNumber: '100C', mobileNumber: '8983290578', documentId: 'DOC2' },
  // { plotNumber: '13', ownerName: 'Jhon', surveyNumber: '22A', mobileNumber: '8983290578', documentId: 'DOC3' },
  // { plotNumber: '14', ownerName: 'Beryllium', surveyNumber: '201D', mobileNumber: '8983290578', documentId: 'DOC4' },
  // { plotNumber: '15', ownerName: 'Boron', surveyNumber: '88B', mobileNumber: '8983290578', documentId: 'DOC5' }
// ];

@Component({
  selector: "app-plot-owner-list",
  templateUrl: "./plot-owner-list.component.html",
  styleUrls: ["./plot-owner-list.component.css"]
})
export class PlotOwnerListComponent implements OnInit {
  userPlotInfo: UserPlotInfo[];
  ventureId: string;

  constructor(
    private pltOnrConnectSrvc: PlotOwnerConnectService,
    private router: ActivatedRoute
  ) {
    this.ventureId = this.router.snapshot.paramMap.get("ventureId");
  }
  displayedColumns: string[] = [
    "plotNumber",
    "ownerName",
    "surveyNumber",
    "mobileNumber"
  ];
  dataSource = [];
  ngOnInit() {
    const result = []
    this.pltOnrConnectSrvc
      .findUsersBySurveryId(this.ventureId)
      .subscribe((data: UserPlotInfo[]) => {
        this.userPlotInfo = data;
        if (null !== this.userPlotInfo && this.userPlotInfo !== undefined) {
          this.userPlotInfo.forEach(function(element) {
            let userInfo = {
              plotNumber: element.plotNumber.plotNumber,
              ownerName: element.user.firstName + " " + element.user.lastName,
              surveyNumber: element.surveyNumber.surveyNum,
              mobileNumber: "Not Supporting Mobile"
            };
            result.push(userInfo);
          });
        }
        this.dataSource =result;
      });
  }
}
