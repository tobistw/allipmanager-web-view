import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITblAsp } from 'app/shared/model/tbl-asp.model';
import { TblAspService } from './tbl-asp.service';
import { ITblVertrag } from 'app/shared/model/tbl-vertrag.model';
import { TblVertragService } from 'app/entities/tbl-vertrag';
import { ITndSiemens } from 'app/shared/model/tnd-siemens.model';
import { TndSiemensService } from 'app/entities/tnd-siemens';

@Component({
    selector: 'jhi-tbl-asp-update',
    templateUrl: './tbl-asp-update.component.html'
})
export class TblAspUpdateComponent implements OnInit {
    tblAsp: ITblAsp;
    isSaving: boolean;

    tblvertrags: ITblVertrag[];

    tndsiemens: ITndSiemens[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tblAspService: TblAspService,
        private tblVertragService: TblVertragService,
        private tndSiemensService: TndSiemensService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tblAsp }) => {
            this.tblAsp = tblAsp;
        });
        this.tblVertragService.query().subscribe(
            (res: HttpResponse<ITblVertrag[]>) => {
                this.tblvertrags = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.tndSiemensService.query().subscribe(
            (res: HttpResponse<ITndSiemens[]>) => {
                this.tndsiemens = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tblAsp.id !== undefined) {
            this.subscribeToSaveResponse(this.tblAspService.update(this.tblAsp));
        } else {
            this.subscribeToSaveResponse(this.tblAspService.create(this.tblAsp));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITblAsp>>) {
        result.subscribe((res: HttpResponse<ITblAsp>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTblVertragById(index: number, item: ITblVertrag) {
        return item.id;
    }

    trackTndSiemensById(index: number, item: ITndSiemens) {
        return item.id;
    }
}
