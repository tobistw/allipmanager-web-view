import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAbnahmeprotokoll } from 'app/shared/model/abnahmeprotokoll.model';
import { AbnahmeprotokollService } from './abnahmeprotokoll.service';

@Component({
    selector: 'jhi-abnahmeprotokoll-delete-dialog',
    templateUrl: './abnahmeprotokoll-delete-dialog.component.html'
})
export class AbnahmeprotokollDeleteDialogComponent {
    abnahmeprotokoll: IAbnahmeprotokoll;

    constructor(
        private abnahmeprotokollService: AbnahmeprotokollService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.abnahmeprotokollService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'abnahmeprotokollListModification',
                content: 'Deleted an abnahmeprotokoll'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-abnahmeprotokoll-delete-popup',
    template: ''
})
export class AbnahmeprotokollDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ abnahmeprotokoll }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AbnahmeprotokollDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.abnahmeprotokoll = abnahmeprotokoll;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
