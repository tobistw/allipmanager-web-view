import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITblAsp } from 'app/shared/model/tbl-asp.model';

@Component({
    selector: 'jhi-tbl-asp-detail',
    templateUrl: './tbl-asp-detail.component.html'
})
export class TblAspDetailComponent implements OnInit {
    tblAsp: ITblAsp;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tblAsp }) => {
            this.tblAsp = tblAsp;
        });
    }

    previousState() {
        window.history.back();
    }
}
