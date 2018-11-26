import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITblFunkmessung } from 'app/shared/model/tbl-funkmessung.model';
import { Principal } from 'app/core';
import { TblFunkmessungService } from './tbl-funkmessung.service';

@Component({
    selector: 'jhi-tbl-funkmessung',
    templateUrl: './tbl-funkmessung.component.html'
})
export class TblFunkmessungComponent implements OnInit, OnDestroy {
    tblFunkmessungs: ITblFunkmessung[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private tblFunkmessungService: TblFunkmessungService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.tblFunkmessungService.query().subscribe(
            (res: HttpResponse<ITblFunkmessung[]>) => {
                this.tblFunkmessungs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTblFunkmessungs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITblFunkmessung) {
        return item.id;
    }

    registerChangeInTblFunkmessungs() {
        this.eventSubscriber = this.eventManager.subscribe('tblFunkmessungListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
