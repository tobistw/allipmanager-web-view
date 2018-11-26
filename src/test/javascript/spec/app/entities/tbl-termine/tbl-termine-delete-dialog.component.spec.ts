/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { TblTermineDeleteDialogComponent } from 'app/entities/tbl-termine/tbl-termine-delete-dialog.component';
import { TblTermineService } from 'app/entities/tbl-termine/tbl-termine.service';

describe('Component Tests', () => {
    describe('TblTermine Management Delete Component', () => {
        let comp: TblTermineDeleteDialogComponent;
        let fixture: ComponentFixture<TblTermineDeleteDialogComponent>;
        let service: TblTermineService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [TblTermineDeleteDialogComponent]
            })
                .overrideTemplate(TblTermineDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TblTermineDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TblTermineService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
