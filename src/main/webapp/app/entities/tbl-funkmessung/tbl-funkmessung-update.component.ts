import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { ITblFunkmessung } from 'app/shared/model/tbl-funkmessung.model';
import { TblFunkmessungService } from './tbl-funkmessung.service';
import { ITblTermine } from 'app/shared/model/tbl-termine.model';
import { TblTermineService } from 'app/entities/tbl-termine';

@Component({
    selector: 'jhi-tbl-funkmessung-update',
    templateUrl: './tbl-funkmessung-update.component.html'
})
export class TblFunkmessungUpdateComponent implements OnInit {
    tblFunkmessung: ITblFunkmessung;
    isSaving: boolean;

    tbltermines: ITblTermine[];
    funDatumDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private tblFunkmessungService: TblFunkmessungService,
        private tblTermineService: TblTermineService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tblFunkmessung }) => {
            this.tblFunkmessung = tblFunkmessung;
        });
        this.tblTermineService.query().subscribe(
            (res: HttpResponse<ITblTermine[]>) => {
                this.tbltermines = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tblFunkmessung.id !== undefined) {
            this.subscribeToSaveResponse(this.tblFunkmessungService.update(this.tblFunkmessung));
        } else {
            this.subscribeToSaveResponse(this.tblFunkmessungService.create(this.tblFunkmessung));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITblFunkmessung>>) {
        result.subscribe((res: HttpResponse<ITblFunkmessung>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackTblTermineById(index: number, item: ITblTermine) {
        return item.id;
    }
}
