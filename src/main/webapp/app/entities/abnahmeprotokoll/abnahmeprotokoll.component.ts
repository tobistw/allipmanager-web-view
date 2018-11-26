import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IAbnahmeprotokoll } from 'app/shared/model/abnahmeprotokoll.model';
import { Principal } from 'app/core';
import { AbnahmeprotokollService } from './abnahmeprotokoll.service';

@Component({
    selector: 'jhi-abnahmeprotokoll',
    templateUrl: './abnahmeprotokoll.component.html'
})
export class AbnahmeprotokollComponent implements OnInit, OnDestroy {
    abnahmeprotokolls: IAbnahmeprotokoll[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private abnahmeprotokollService: AbnahmeprotokollService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.abnahmeprotokollService.query().subscribe(
            (res: HttpResponse<IAbnahmeprotokoll[]>) => {
                this.abnahmeprotokolls = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAbnahmeprotokolls();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAbnahmeprotokoll) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInAbnahmeprotokolls() {
        this.eventSubscriber = this.eventManager.subscribe('abnahmeprotokollListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
