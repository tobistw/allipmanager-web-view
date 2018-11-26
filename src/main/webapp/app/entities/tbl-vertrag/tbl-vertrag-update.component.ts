import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ITblVertrag } from 'app/shared/model/tbl-vertrag.model';
import { TblVertragService } from './tbl-vertrag.service';

@Component({
    selector: 'jhi-tbl-vertrag-update',
    templateUrl: './tbl-vertrag-update.component.html'
})
export class TblVertragUpdateComponent implements OnInit {
    tblVertrag: ITblVertrag;
    isSaving: boolean;

    constructor(private tblVertragService: TblVertragService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tblVertrag }) => {
            this.tblVertrag = tblVertrag;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tblVertrag.id !== undefined) {
            this.subscribeToSaveResponse(this.tblVertragService.update(this.tblVertrag));
        } else {
            this.subscribeToSaveResponse(this.tblVertragService.create(this.tblVertrag));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITblVertrag>>) {
        result.subscribe((res: HttpResponse<ITblVertrag>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
