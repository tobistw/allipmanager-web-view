import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITblVertrag } from 'app/shared/model/tbl-vertrag.model';

@Component({
    selector: 'jhi-tbl-vertrag-detail',
    templateUrl: './tbl-vertrag-detail.component.html'
})
export class TblVertragDetailComponent implements OnInit {
    tblVertrag: ITblVertrag;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tblVertrag }) => {
            this.tblVertrag = tblVertrag;
        });
    }

    previousState() {
        window.history.back();
    }
}
