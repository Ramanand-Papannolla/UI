import { ScrollDispatchModule } from "@angular/cdk/scrolling";
import { NgModule } from "@angular/core";
import { FlexLayoutModule } from "@angular/flex-layout";
import { MatButtonModule, MatFormFieldModule, MatGridListModule, MatIconModule, MatListModule, MatSidenavModule, MatSnackBarModule, MatTableModule, MatToolbarModule } from "@angular/material";
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonToggleModule } from "@angular/material/button-toggle";
import { MatCardModule } from "@angular/material/card";
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTooltipModule } from "@angular/material/tooltip";
const MATERIALCOMPONENTS = [
  MatSidenavModule,
  MatToolbarModule,
  MatIconModule,
  MatListModule,
  MatCardModule,
  MatTooltipModule,
  FlexLayoutModule,
  ScrollDispatchModule,
  MatTableModule,
  MatInputModule,
  MatFormFieldModule,
  MatGridListModule,
  MatInputModule,
  MatButtonModule,
  MatListModule,
  MatSnackBarModule,
  MatButtonToggleModule,
  MatSelectModule,
  MatAutocompleteModule
]
@NgModule({
  imports: [MATERIALCOMPONENTS],
  exports: [MATERIALCOMPONENTS]
})
export class MaterialModule { }
