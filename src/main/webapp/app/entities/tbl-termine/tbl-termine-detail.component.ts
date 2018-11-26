import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITblTermine } from 'app/shared/model/tbl-termine.model';

@Component({
    selector: 'jhi-tbl-termine-detail',
    templateUrl: './tbl-termine-detail.component.html'
})
export class TblTermineDetailComponent implements OnInit {
    tblTermine: ITblTermine;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tblTermine }) => {
            this.tblTermine = tblTermine;
        });
    }

    previousState() {
        window.history.back();
    }
}
